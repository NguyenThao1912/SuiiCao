package com.monsun.suiicao.views.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityTongKetBinding;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.chatting.message.MessageActivity;
import com.monsun.suiicao.views.studentresult.StudentResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    private ActivityTongKetBinding binding;
    private int studentId;
    private List<Semester> list = new ArrayList<>();

    private ApiInstance apiInstance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tong_ket);
        apiInstance = new ApiInstance();
        setData();
        getStudentId();

        setSupportActionBar(binding.studentResultToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            }
            return true;
    }

    private void getStudentId(){
        if(AppVar.mStudent!=null){
            studentId = AppVar.mStudent.getStudentId();
        }
        else {
            Intent intent =getIntent();
            studentId = intent.getIntExtra("Sid",0);
        }
    }
    public static Intent newIntent(Context context) {
        return new Intent(context, ResultActivity.class);
    }
    public void setData()
    {
        Call<List<Semester>> lsemester = apiInstance.getServices().getSemester();
        lsemester.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("", "onResponse: Get semester failed");
                }
                else
                {
                    list = response.body();
                    Collections.reverse(list);
                    list = list.subList(0,4);

                    binding.viewPager.setAdapter(new ResultViewPagerAdapter(getSupportFragmentManager(),studentId,list));
                    binding.tab.setupWithViewPager(binding.viewPager);
                }
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Log.d("", "onResponse: Get semester failed " + t.getMessage() );
            }
        });


    }
}
