package com.monsun.suiicao.views.useraccount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.FragmentUserAccountBinding;
import com.monsun.suiicao.views.base.BaseFragment;
import com.monsun.suiicao.views.login.LoginActivity;
import com.monsun.suiicao.views.useraccount.studentinformation.StudentInformationActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userAccountFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userAccountFrag extends BaseFragment implements IUserHandler {
    private static final String TAG = "userAccountFrag";
    private FragmentUserAccountBinding binding;
    private UserViewModel viewModel;

    public userAccountFrag() {
        // Required empty public constructor
    }

    public static userAccountFrag newInstance() {
        userAccountFrag fragment = new userAccountFrag();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_account;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_user_account,container, false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        viewModel.setNavigator(this);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void OpenLoginActivity() {
        Log.d(TAG, "OpenLoginActivity: ");
        Intent t = LoginActivity.newIntent(getActivity());
        startActivity(t);
        getActivity().finish();

        // TODO logging out user
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        AppVar.mStudent = null;
    }

    @Override
    public void OpenStudentInfor() {
        Log.d(TAG, "OpenStudentInfor: hahhaha");
        Intent t = StudentInformationActivity.newIntent(getActivity());
        startActivity(t);
    }

}