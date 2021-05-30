package com.monsun.suiicao.views.liststudent.studentinformation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityStudentInfoBinding;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.chatting.message.MessageActivity;
import com.monsun.suiicao.views.result.ResultActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_info extends BaseActivity implements IStudentinfo , OnClickListener {
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
        StorageReference storageReference  = FirebaseStorage.getInstance().getReference().child(users.getStudentId().toString());
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Student_info.this)
                        .load(storageReference)
                        .into(binding.studentInforImg);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
        binding.updateEvaluation.setOnClickListener(this);

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
            case R.id.student_infor_result:{
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("Sid",users.getStudentId());
                startActivity(intent);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
    private void UpdateEvaluation()
    {
        ApiInstance apiInstance = new ApiInstance();
        Call<String> call = apiInstance.getServices().Update_Student_Evaluation(users.getStudentId(),binding.getData().getEvaluation());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Student_info.this, "Update thanh cong " + response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_evaluation: {
                UpdateEvaluation();
            }
        }
    }
}