package com.monsun.suiicao.views.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.views.base.BaseFragment;
import com.monsun.suiicao.views.curriculum.CurriculumActivity;
import com.monsun.suiicao.views.study.StudentExamActivity;
import com.monsun.suiicao.views.timetable.TimetableActivity;


public class HomeFragment extends BaseFragment implements IHomeHandler {

    private CardView study_summary,curriculum,schedule;
    private TextView greeting;
    public View v;
    public HomeFragment() {
        // Required empty public constructor

    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    private void setWiget()
    {
        greeting = v.findViewById(R.id.greeting);
        study_summary = v.findViewById(R.id.feature_study_exam);
        schedule = v.findViewById(R.id.feature_2);
        curriculum = v.findViewById(R.id.feature_3);
        if (AppVar.currentuser != null)
        {
            greeting.setText("Xin Chào " + AppVar.currentuser.getFullName());
        }
        study_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenStudentExam();
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { OpenSchedule(); }
        });
        curriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { OpenCurriculum(); }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        setWiget();
        return v;
    }

    @Override
    public void OpenStudentExam() {
        Intent i = StudentExamActivity.newIntent(getActivity());
        startActivity(i);
    }

    @Override
    public void OpenCurriculum() {
        Intent i = CurriculumActivity.newIntent(getActivity());
        startActivity(i);
    }

    @Override
    public void OpenSchedule() {
        Intent i = TimetableActivity.newIntent(getActivity());
        startActivity(i);
    }
}