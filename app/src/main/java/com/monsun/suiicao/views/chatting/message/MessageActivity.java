package com.monsun.suiicao.views.chatting.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.databinding.ActivityMessageBinding;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.models.Chat;
import com.monsun.suiicao.service.FCMSendData;
import com.monsun.suiicao.service.IFCMService;
import com.monsun.suiicao.service.RetrofitFCMClient;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.main.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MessageActivity extends BaseActivity implements IMessage {
    private static final String TAG = "MessageActivity";
    private CircleImageView imageView;
    private TextView name;
    private ActivityMessageBinding binding;
    private MessageViewModel viewModel;
    private RecyclerView recyclerView;
    private Intent i;
    private IFCMService ifcmService;
    private String RoomID;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_message);
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        ifcmService = RetrofitFCMClient.getInstance().create(IFCMService.class);
        SetWidget();

    }

    private void SetWidget()
    {
        i = getIntent();
        imageView = findViewById(R.id.message_img);
        name = findViewById(R.id.message_name);
        //================ TODO cheps code
        recyclerView = findViewById(R.id.message_chat);
        //TODO Get firebase uid

        ReadMessage(FirebaseSer.mAuth.getUid(),i.getStringExtra("uid"),"");
        //=============
        // Set data

        name.setText(i.getStringExtra("name"));
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    private void ReadMessage(String myid, String receiverid, String img)
    {
        try {
            Log.d(TAG, "ReadMessage: ");
            List<Chat> chats =  new ArrayList<>();
            chats.clear();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("chats");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren())
                    {
                        Chat chat = data.getValue(Chat.class);
                        if (chat.getReceiver().equals(myid) && chat.getSender().equals(receiverid) ||
                                chat.getReceiver().equals(receiverid) && chat.getSender().equals(myid))
                            chats.add(chat);
                    }
                    MessageAdapter adapter = new MessageAdapter(chats);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessageActivity.this);
                    linearLayoutManager.setStackFromEnd(true);
                    recyclerView.setLayoutManager(linearLayoutManager);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch (Exception e)
        {
            Log.d(TAG, "ReadMessage: " + e.getMessage());
        }

    }
    private void SendToSever(String message)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        try {

            String senderid = FirebaseSer.mAuth.getUid();
            String receiverid = i.getStringExtra("uid");
            Map<String, String> chat = new HashMap<>();
            chat.put("sender",senderid);
            chat.put("receiver",receiverid);
            chat.put("message",message);
            chat.put("createAt", Calendar.getInstance().getTime().toString());
            chat.put("chatID", java.util.UUID.randomUUID().toString().replace("-", ""));
            ref.child("chats").push().setValue(chat);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                    .getReference("ChatList")
                    .child(FirebaseSer.mAuth.getUid())
                    .child(receiverid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists())
                    {
                        databaseReference.child("id").setValue(receiverid);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            String sendername ="";
            if (AppVar.mMentor != null)
                sendername = AppVar.mMentor.getMentorName();
            else
                sendername = AppVar.mStudent.getFullName();
            sendMessagetoUser(sendername,receiverid,message);
        }
        catch (Exception e)
        {
            Log.d(TAG, "SendToSever: " + e.getMessage());
        }
    }

    private void sendMessagetoUser(String sendername, String receiverid, String message) {
        Map<String,String> notiData = new HashMap<>();
        notiData.put(CommonUtils.NOTI_TITLE,"Message from " + sendername);
        notiData.put(CommonUtils.NOTI_CONTENT,message);
        notiData.put(CommonUtils.NOTI_SENDER, FirebaseAuth.getInstance().getCurrentUser().getUid());
        notiData.put(CommonUtils.NOTI_ROOMID,CommonUtils.ROOM_SELECTED);
        notiData.put(CommonUtils.NOTI_RECEIVER,receiverid);

        FCMSendData sendData = new FCMSendData("/topics/"+ CommonUtils.ROOM_SELECTED,notiData);
        compositeDisposable.add(
                ifcmService.sendNotification(sendData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fcmResponse -> {
                    Toast.makeText(this, "Request", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                })
        );
    }


    @Override
    public void SendMessage(String message) {
        // TODO Send to sever
        if (message.equals(""))
        {
            return;
        }
        else
        {
            SendToSever(message);
            Log.d(TAG, "SendMessage: ");
            ReadMessage(FirebaseSer.mAuth.getUid(),i.getStringExtra("uid"),"");
        }

    }

    @Override
    public void goback() {
        finish();
    }
}