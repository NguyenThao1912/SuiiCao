package com.monsun.suiicao.views.curriculum;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurriculumViewModel extends BaseViewModel<ICurriculum> {
    private static final String TAG = "CurriculumViewModel";
    public MutableLiveData<List<Curriculum>> data = new MutableLiveData<>();
    ApiInstance instance;
    public CurriculumViewModel()
    {
        instance = new ApiInstance();
        getdata();
    }
    public MutableLiveData<List<Curriculum>> getData() {
        return data;
    }

    private void getdata() {
        Call<List<Curriculum>> services = instance.getServices().getStudentLecture(Integer.parseInt(AppVar.mStudent.getClassId()));
        services.enqueue(new Callback<List<Curriculum>>() {
            @Override
            public void onResponse(Call<List<Curriculum>> call, Response<List<Curriculum>> response) {
                if (!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse: Error ");
                }
                else
                {
                    List<Curriculum> curriculumList = response.body();
                    data.setValue(curriculumList);
                }
            }

            @Override
            public void onFailure(Call<List<Curriculum>> call, Throwable t) {

            }
        });
    }

}
