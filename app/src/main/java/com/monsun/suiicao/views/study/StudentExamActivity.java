package com.monsun.suiicao.views.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityStudentExamBinding;
import com.monsun.suiicao.models.CourseExam;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.List;

public class StudentExamActivity extends BaseActivity implements IStudentExam {

    private static final String TAG = "StudentExamActivity";
    private StudentExamViewModel viewModel;
    private ActivityStudentExamBinding binding;
    private Spinner spinner;
    private RecyclerView recyclerView;
    ExamAdapter examAdapter;
    public static Intent newIntent(Context context) {
        return new Intent(context, StudentExamActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exam);
        setIsLoading(true);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_student_exam);
        viewModel = new ViewModelProvider(this).get(StudentExamViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        getWidget();
        viewModel.getListSemester().observe(this, new Observer<List<Semester>>() {
            @Override
            public void onChanged(List<Semester> semesters) {

                ArrayAdapter<Semester> adapter = new ArrayAdapter<Semester>(StudentExamActivity.this,
                        R.layout.support_simple_spinner_dropdown_item,semesters);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection(semesters.size()-1);
               // semester.setSelection(adapter.getPosition());
                setIsLoading(false);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Semester s = (Semester) adapterView.getSelectedItem();
                viewModel.setCurrentSemester(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        viewModel.getExamlist().observe(this, new Observer<List<CourseExam>>() {
            @Override
            public void onChanged(List<CourseExam> courseExams) {
                Log.d(TAG, "onChanged: " );
                if (courseExams != null && courseExams.size() > 1)
                {
                    examAdapter = null;
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentExamActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    examAdapter = new ExamAdapter(courseExams);
                    recyclerView.setAdapter(examAdapter);
                }

            }
        });
    }
    private void getWidget()
    {
        spinner = findViewById(R.id.choose_semester);
        recyclerView = findViewById(R.id.list_exam);
    }

    public void onBackPressed() {
        finish();
    }
    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void setIsLoading(Boolean isLoading) {
        if (isLoading)
            showLoading();
        else
            hideLoading();
    }
}