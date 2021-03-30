package com.monsun.suiicao.views.chatting.frag.ListChat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.models.ChatList;
import com.monsun.suiicao.models.Contact;
import com.monsun.suiicao.views.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends BaseFragment implements ChatAdapter.OnRecentChatListener{
    private static final String TAG = "ChatFragment";
    public View v;
    private DatabaseReference databaseReference;
    private List<Contact> contactList;
    private List<ChatList> chatLists;
    private RecyclerView recyclerView;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_recent_chat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_recent_chat, container, false);
        recyclerView = v.findViewById(R.id.list_chat);
       // GetChatList();
        return v;
    }
    private void GetChatList()
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser User = auth.getCurrentUser();
        chatLists = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("ChatList").child(FirebaseSer.FireAuth_User.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange: ");
                chatLists.clear();
                for (DataSnapshot data:snapshot.getChildren())
                {
                    ChatList c = snapshot.getValue(ChatList.class);
                    chatLists.add(c);
                }
                GetListRecentContact();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void GetListRecentContact()
    {

        Log.d(TAG, "GetListRecentContact: " + chatLists.size());
        contactList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("class_" + AppVar.mStudent.getClassId());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactList.clear();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Contact contact = data.getValue(Contact.class);
                    for (ChatList c:chatLists)
                    {
                        if (contact.getUid().equals(String.valueOf(c.getId())))
                        {
                            contactList.add(contact);
                        }
                    }
                }
                ChatAdapter adapter = new ChatAdapter(contactList,ChatFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void OnRecentChatClick(int position) {
        Toast.makeText(getActivity(), "1111", Toast.LENGTH_SHORT).show();
    }
}