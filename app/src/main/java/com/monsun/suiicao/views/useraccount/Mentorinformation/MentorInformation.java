package com.monsun.suiicao.views.useraccount.Mentorinformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityMentorInformationBinding;
import com.monsun.suiicao.views.base.BaseActivity;

public class MentorInformation extends BaseActivity implements IMentorInfomation {
    ActivityMentorInformationBinding binding;
    MentorInformationViewModel viewModel;
    public static Intent newIntent(Context context){
        return new Intent(context,MentorInformation.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIsLoading(true);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mentor_information);
        viewModel = new ViewModelProvider(this).get(MentorInformationViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.mentorInformation);
        binding.setLifecycleOwner(this);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewModel.getMentor().observe(this, mentor -> {
            binding.setData(mentor);
            setIsLoading(false);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
            {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        if (isLoading)
            showLoading();
        else
            hideLoading();
    }
}