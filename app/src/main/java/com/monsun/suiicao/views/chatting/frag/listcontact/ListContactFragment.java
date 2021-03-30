package com.monsun.suiicao.views.chatting.frag.listcontact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.FragmentListContactBinding;
import com.monsun.suiicao.models.Contact;
import com.monsun.suiicao.views.base.BaseFragment;
import com.monsun.suiicao.views.chatting.message.MessageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListContactFragment extends BaseFragment implements ListContactAdapter.OnContactListener,IListContact {
    private static final String TAG = "ListContactFragment";
    private RecyclerView recyclerView;
    private View v;
    private FragmentListContactBinding binding;
    private ListContactviewmodel viewModel;
    private ListContactAdapter adapter;
    private List<Contact> contactList = new ArrayList<>();
    public ListContactFragment() {
        // Required empty public constructor
    }
    public static ListContactFragment newInstance() {
        ListContactFragment fragment = new ListContactFragment();
        return fragment;
    }

    public static ListContactFragment newInstance(String param1, String param2) {
        ListContactFragment fragment = new ListContactFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_contact;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ");
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_list_contact,container, false);
        viewModel = new ViewModelProvider(this).get(ListContactviewmodel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        viewModel.setNavigator(this);
        v = binding.getRoot();
        recyclerView = v.findViewById(R.id.list_contact);
        GetUser("class_" + AppVar.mStudent.getClassId());
        adapter = new ListContactAdapter(contactList,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return v ;
    }


    @Override
    public void onContactClick(int position) {
        Log.d(TAG, "onContactClick: " + position);
        Intent intent =  new Intent(getActivity(), MessageActivity.class);
        intent.putExtra("uid",contactList.get(position).getUid());
        intent.putExtra("name",contactList.get(position).getContact_name());
        intent.putExtra("img",contactList.get(position).getContact_img());
        startActivity(intent);
    }



    private  void GetUser(String url) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(url);
        List<Contact> contacts = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contacts.clear();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Contact contact = data.getValue(Contact.class);
                    //if (contact.getUid() != FireAuth_User.getUid() )
                    contacts.add(contact);
                }
                contactList = contacts;
                adapter = new ListContactAdapter(contacts,ListContactFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void SetIsLoading(Boolean isloading) {
            if (isloading)
                showLoading();
            else
                hideLoading();
    }
}