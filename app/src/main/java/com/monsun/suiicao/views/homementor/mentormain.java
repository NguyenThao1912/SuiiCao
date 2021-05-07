package com.monsun.suiicao.views.homementor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.FragmentMentormainBinding;
import com.monsun.suiicao.views.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mentormain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mentormain extends BaseFragment implements IMentormain {
    private static final String TAG = "HomeFragment";
    private FragmentMentormainBinding binding;
    private static MentormainViewModel viewModel;
    public mentormain() {
        // Required empty public constructor

    }

    public static mentormain newInstance() {
        mentormain fragment = new mentormain();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mentormain;
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
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_mentormain,container, false);
        viewModel = new ViewModelProvider(this).get(MentormainViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        viewModel.setNavigator(this);
        View v = binding.getRoot();
        return v;
    }


}