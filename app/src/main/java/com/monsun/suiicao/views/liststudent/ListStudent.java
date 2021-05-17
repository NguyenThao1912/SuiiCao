package com.monsun.suiicao.views.liststudent;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityListStudentBinding;
import com.monsun.suiicao.databinding.DialogSendMessageBinding;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.task.SendMessageAysncTask;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.liststudent.studentinformation.Student_info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class ListStudent extends BaseActivity implements IListStudent, SearchView.OnQueryTextListener, ListStudentAdapter.OnItemCheckListener {
    private static final String TAG = "ListStudent - Activity";
    private ListStudentViewModel viewModel;
    private ActivityListStudentBinding binding;
    private List<Users> data;
    private ListStudentAdapter adapter;
    private List<Users> checked_users = new ArrayList<>();
    private Map<String,String> Uid_Userid = new HashMap<>();
    List<String> Uid = new ArrayList<>();

    DialogSendMessageBinding dialogbinding;
    private long backpresstimes;
    Dialog dialog;
    SearchView searchView;
    EditText mentor_message;
    public static Intent newIntent(Context context) {
        return new Intent(context, ListStudent.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_student);
        viewModel = new ViewModelProvider(this).get(ListStudentViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListStudent.this,LinearLayoutManager.VERTICAL,false);
        binding.listStudent.setLayoutManager(linearLayoutManager);
        setSupportActionBar(binding.listStudentToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // lấy danh sách uid của sinh viên trên firebase
       // GetUID("class_" + AppVar.mMentor.getClassId());
        Uid.add("LWvc5tIlKTYJAaLWVllIfGdCMc73");
        Uid.add("WcoRd3ZSEsWgecdAVw7TeOZ3JZh1");
        Uid.add("Y3MP9GRW07TMqQxPkkPxfa1DpXA2");
        Uid.add("zH1M8pcU6TWum2SWzVJCcYDsxbm2");

        viewModel.user.observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                data = users;
                adapter = new ListStudentAdapter(data,ListStudent.this);
                binding.listStudent.setAdapter(adapter);
            }
        });
        binding.selectAllStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    adapter.selectAll(binding.selectAllStudent.isChecked());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_student_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.list_student_search_bar);
        searchView = (SearchView) searchItem.getActionView();
        // Configure the search info and add any event listeners...
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.list_student_send_message_multiple:{
                show_dialog_send_message();
                //TODO open dialog gửi tin nhắn những sinh viên được check
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goback() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified())
            searchView.setIconified(true);
        else
        {
            if (backpresstimes + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(this, "Press back again to exit the program", Toast.LENGTH_SHORT).show();
            }
            backpresstimes = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        binding.listStudent.scrollToPosition(0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        binding.listStudent.scrollToPosition(0);
        return false;
    }

    @Override
    public void onItemCheck(Users users) {
        if (!checked_users.contains(users))
            checked_users.add(users);
        Log.d(TAG, "onItemCheck: " + users.getFullName() + " " + checked_users.size());
    }

    @Override
    public void onItemUnCheck(Users users) {
        if (checked_users.contains(users))
            checked_users.remove(users);
        Log.d(TAG, "onItemUnCheck: " + users.getFullName() + " " + checked_users.size());
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Student_info.class);
        data.get(position).setUid(Uid.get(position));
        intent.putExtra("studentinfo",data.get(position));
        startActivity(intent);
    }

    public void show_dialog_send_message()
    {
        if (checked_users.size() > 0)
        {
            dialog = new Dialog(this);
            dialogbinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_send_message, null, false);
            dialog.setContentView(dialogbinding.getRoot());
            dialog.setTitle("Gửi tin nhắn");
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            dialog.getWindow().setAttributes(lp);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "Bạn cần tích chọn sinh viên trước khi sử dụng tính năng này", Toast.LENGTH_SHORT).show();
        }
    }
    public void SendMessagetostudent(View v)
    {
        if (!dialogbinding.listStudentMessage.getText().toString().isEmpty())
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Bạn có muốn gửi tin nhắn cho " + checked_users.size() + " sinh viên ? ");
            alertDialogBuilder.setMessage("Xác nhận gửi tin nhắn");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

//                            for (Users users : checked_users){
//                                Uid.add(Uid_Userid.get(users.getStudentId()));
//                                Log.d(TAG, "onClick: Uid = " + Uid_Userid.get(users.getStudentId()) );
//                            }
                            Toast.makeText(ListStudent.this, "Đang gửi tin", Toast.LENGTH_SHORT).show();
                            SendMessageAysncTask send = new SendMessageAysncTask(ListStudent.this,dialogbinding.listStudentMessage.getText().toString(),Uid);
                            send.executeOnExecutor(Executors.newFixedThreadPool(1));

                            dialog.dismiss();
                        }
                    });
            alertDialogBuilder.setNegativeButton("CANCEL",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else
        {
            Toast.makeText(this, "Bạn cần thêm nội dung tin nhắn ", Toast.LENGTH_SHORT).show();
        }
    }
    private void Uncheckallstudent()
    {
        for(Users u : data)
        {
            onItemUnCheck(u);
        }
    }

   /* private void GetUID(String classid)
    {
            DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child(classid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    try {
                        if (task.isSuccessful()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            for (DataSnapshot data: dataSnapshot.getChildren()) {
                                Contact contact = data.getValue(Contact.class);
                                Uid_Userid.put(contact.getContact_id(),contact.getUid());
                                Log.d(TAG, "Uid_userid: " +  Uid_Userid.get(contact.getContact_id()));
                            }
                        }
                        else {
                            Log.d("firebasexxx", String.valueOf(task.getResult().getValue()));
                        }
                    }
                    catch (Exception e)
                    {
                        Log.d(TAG, "GetUID: " + e.getMessage());
                    }
                }
            });
    }*/

}