package com.monsun.suiicao.views.liststudent.studentinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityStudentInfoBinding;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.chatting.message.MessageActivity;

import java.util.Objects;

public class Student_info extends BaseActivity implements IStudentinfo  {
    ActivityStudentInfoBinding binding;
    StudentInfoViewModel viewModel;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_student_info);
        viewModel = new ViewModelProvider(this).get(StudentInfoViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.studentInfoToolbar);
        ActionBar ab = getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        users = (Users) intent.getExtras().getSerializable("studentinfo");
        binding.setData(users);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_infor_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.student_infor_message:{
                Intent intent =  new Intent(this, MessageActivity.class);
                intent.putExtra("uid",users.getUid());
                intent.putExtra("name",users.getFullName());
                intent.putExtra("img",users.getImg());
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}