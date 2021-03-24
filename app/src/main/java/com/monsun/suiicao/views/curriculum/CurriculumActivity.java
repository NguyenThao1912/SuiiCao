package com.monsun.suiicao.views.curriculum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityCurriculumBinding;
import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.List;

public class CurriculumActivity extends BaseActivity implements ICurriculum {
    private static final String TAG = "CurriculumActivity";
    ActivityCurriculumBinding binding;
    CurriculumViewModel viewModel;
    RecyclerView lectures;
    List<Curriculum> currentCurriculum;
    public static Intent newIntent(Context context) {
        return new Intent(context, CurriculumActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_curriculum);
        viewModel = new ViewModelProvider(this).get(CurriculumViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        initWidget();

        viewModel.getData().observe(this, new Observer<List<Curriculum>>() {
            @Override
            public void onChanged(List<Curriculum> curricula) {
                currentCurriculum = curricula;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CurriculumActivity.this,LinearLayoutManager.VERTICAL,false);
                lectures.setLayoutManager(linearLayoutManager);
                CurriculumAdapter examAdapter = new CurriculumAdapter(currentCurriculum);
                lectures.setAdapter(examAdapter);
            }
        });
    }

    private void initWidget()
    {
        lectures = findViewById(R.id.list_lecture);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void goBack() {
        finish();
    }
}