package com.monsun.suiicao.views.chatting.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityMessageBinding;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.models.Chat;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.main.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends BaseActivity implements IMessage {
    private static final String TAG = "MessageActivity";
    private CircleImageView imageView;
    private TextView name;
    private ActivityMessageBinding binding;
    private MessageViewModel viewModel;
    private RecyclerView recyclerView;
    private Intent i;
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_message);
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
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
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
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
        }
        catch (Exception e)
        {
            Log.d(TAG, "SendToSever: " + e.getMessage());
        }
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