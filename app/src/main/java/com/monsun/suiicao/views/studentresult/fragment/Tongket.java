package com.monsun.suiicao.views.studentresult.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.FragmentTongKetBinding;
import com.monsun.suiicao.models.Result;
import com.monsun.suiicao.views.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tongket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tongket extends BaseFragment implements ITongKet {

    FragmentTongKetBinding binding;
    private int studentid,semesterid;
    private TongketViewModel viewModel;
    public Tongket(int studentid, int semesterid) {

        this.studentid = studentid;
        this.semesterid = semesterid;
    }

    public Tongket() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Tongket newInstance(int studentid, int semesterid) {
        Tongket fragment = new Tongket(studentid,semesterid);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tong_ket;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tong_ket,container, false);
        viewModel = new ViewModelProvider(this).get(TongketViewModel.class);
        viewModel.setNavigator(this);
        binding.setData(new Result());

        return binding.getRoot();

    }

}