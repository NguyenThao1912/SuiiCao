package com.monsun.suiicao.views.timetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityTimetableBinding;
import com.monsun.suiicao.models.Schedule;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.List;

public class TimetableActivity extends BaseActivity implements  ITimetable{

    private static final String TAG = "TimetableActivity";
    ActivityTimetableBinding binding;
    TimetableViewModel viewModel;
    RecyclerView timetable;
    List<Schedule> currentSemester;
    public static Intent newIntent(Context context) {
        return new Intent(context, TimetableActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_timetable);
        viewModel = new ViewModelProvider(this).get(TimetableViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.timetableToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        initWidget();

        viewModel.getListSchedule().observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedule) {
                currentSemester = schedule;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TimetableActivity.this,LinearLayoutManager.VERTICAL,false);
                timetable.setLayoutManager(linearLayoutManager);
                SubjectAdapter subjectAdapter = new SubjectAdapter(currentSemester);
                timetable.setAdapter(subjectAdapter);
            }
        });
    }

    private void initWidget()
    {
        timetable = findViewById(R.id.list_timetable);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void goBack() {
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}