package com.monsun.suiicao.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.firebase.FirebaseSer;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendMessageAysncTask extends AsyncTask<Void, Void, Void> {
    private String message;
    private List<String> receiveid;
    private Context context;
    public SendMessageAysncTask(Context context, String message, List<String> receiveid)
    {
        this.context = context;
        this.message = message;
        this.receiveid = receiveid;

    }
    @Override
    protected Void doInBackground(Void... voids) {
        try
        {
            for(String i :receiveid)
            {
                SendToSever(message,i);
                Log.d("task", "receiveid: " + i);
            }

        }
        catch (Exception e){
            Log.d("task", "doInBackground: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(context, "Gửi tin thành công", Toast.LENGTH_SHORT).show();
        super.onPostExecute(aVoid);
    }

    private void SendToSever(String message, String receiveid)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String senderid = FirebaseSer.mAuth.getCurrentUser().getUid();
        String receiverid = receiveid;
        Map<String, String> chat = new HashMap<>();
        chat.put("sender",senderid);
        chat.put("receiver",receiverid);
        chat.put("message",message);
        chat.put("createAt", Calendar.getInstance().getTime().toString());
        chat.put("chatID", java.util.UUID.randomUUID().toString().replace("-", ""));
        ref.child("chats").push().setValue(chat);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("ChatList")
                .child(FirebaseSer.mAuth.getCurrentUser().getUid())
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
}

