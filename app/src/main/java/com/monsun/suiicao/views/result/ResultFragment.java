package com.monsun.suiicao.views.result;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.monsun.suiicao.databinding.FragmentTongKetBinding;
import com.monsun.suiicao.models.Result;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.repositories.ApiInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultFragment extends Fragment {
    private FragmentTongKetBinding binding;
    private List<Result> list = new ArrayList<>();
    private int semesterid;
    private int studentid;

    private ApiInstance apiInstance;

    public ResultFragment() {
    }

    public ResultFragment(int semesterid, int studentid) {
        this.semesterid = semesterid;
        this.studentid = studentid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTongKetBinding.inflate(inflater,container,false);
        apiInstance = new ApiInstance();
        setData();
        return binding.getRoot();
    }
    public void setData()
    {
        Call<List<Result>> listCall = apiInstance.getServices().GetStudentResult(studentid,semesterid);
        listCall.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if(!response.isSuccessful())
                {
                    Log.d("", "onResponse: Get semester failed");
                }
                else
                {
                    list = response.body();
                    binding.rc.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rc.setAdapter(new ResultAdapter(list));
                }
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }

        });
    }
}
