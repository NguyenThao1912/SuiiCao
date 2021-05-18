package com.monsun.suiicao.views.studentresult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityTongKetBinding;
import com.monsun.suiicao.views.base.BaseActivity;

public class StudentResult extends BaseActivity implements IStudentResult {
    ActivityTongKetBinding binding;
    StudentResultViewModel viewModel;
    public static Intent newIntent(Context context) {
        return new Intent(context, StudentResult.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tong_ket);
        viewModel = new ViewModelProvider(this).get(StudentResultViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.curriculumToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}