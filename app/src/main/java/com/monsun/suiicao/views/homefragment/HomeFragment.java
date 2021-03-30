package com.monsun.suiicao.views.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.FragmentHomeBinding;
import com.monsun.suiicao.views.base.BaseFragment;
import com.monsun.suiicao.views.curriculum.CurriculumActivity;
import com.monsun.suiicao.views.study.StudentExamActivity;
import com.monsun.suiicao.views.timetable.TimetableActivity;


public class HomeFragment extends BaseFragment implements IHomeHandler {
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private static HomeFragmentViewModel viewModel;
    public HomeFragment() {
        // Required empty public constructor

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_home,container, false);
        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        viewModel.setNavigator(this);
        View v = binding.getRoot();
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