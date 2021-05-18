package com.monsun.suiicao.views.studentresult.fragment;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.models.Result;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TongketViewModel extends BaseViewModel<ITongKet> {
    MutableLiveData<List<Result>> data = new MutableLiveData<>();
    public TongketViewModel()
    {

    }

    public MutableLiveData<List<Result>> getData(int studentid,int semesterid) {
        getResult(studentid,semesterid);
        return data;
    }

    private void getResult(int studentid,int semesterid)
    {
        ApiInstance apiInstance = new ApiInstance();

        Call<List<Result>> call = apiInstance.getServices().GetStudentResult(studentid,semesterid);
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                List<Result> results = response.body();
                data.setValue(results);
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }
        });
    }
}
