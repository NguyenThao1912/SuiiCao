package com.monsun.suiicao.views.liststudent;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListStudentViewModel extends BaseViewModel<IListStudent> {
    MutableLiveData<List<Users>> user = new MutableLiveData<List<Users>>();
    public ListStudentViewModel()
    {
        getdata();
    }

    public void goback()
    {
        getNavigator().goback();
    }
    public void getdata()
    {

        ApiInstance api = new ApiInstance();
        Call<List<Users>> caller = api.getServices().getAllStudentByClassId(AppVar.mMentor.getClassId());
        caller.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                user.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

}
