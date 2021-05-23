package com.monsun.suiicao.views.useraccount.Mentorinformation;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.models.Mentor;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MentorInformationViewModel extends BaseViewModel<IMentorInfomation> {
    MutableLiveData<Mentor> mentor = new MutableLiveData<>();
    public MentorInformationViewModel()
    {
        GetInformation();
    }
    private ApiInstance apiInstance;

    public MutableLiveData<Mentor> getMentor() {
        return mentor;
    }
    public void GetInformation()
    {

        apiInstance = new ApiInstance();
        Call<Mentor> call = apiInstance.getServices().getMentorByClassId(Integer.parseInt(AppVar.mStudent.getClassId()));
        call.enqueue(new Callback<Mentor>() {
            @Override
            public void onResponse(Call<Mentor> call, Response<Mentor> response) {
                mentor.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Mentor> call, Throwable t) {

            }
        });
    }
}
