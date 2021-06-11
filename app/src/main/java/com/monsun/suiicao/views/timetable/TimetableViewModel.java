package com.monsun.suiicao.views.timetable;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.models.Schedule;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableViewModel extends BaseViewModel<ITimetable> {

    private static final String TAG = "TimetableViewModel";
    private MutableLiveData<List<Schedule>> listSchedule = new MutableLiveData<>();

    public void setSemester(Semester current) {
        getSchedule(current.getSemesterKey());
    }

    private ApiInstance apiInstance;
    public TimetableViewModel()
    {
        apiInstance = new ApiInstance();
        getSchedule(AppVar.semester.getSemesterKey());
    }

    public MutableLiveData<List<Schedule>> getListSchedule() {
        return listSchedule;
    }

    private void getSchedule(int semesterid)
    {
        Call<List<Schedule>> listCall = apiInstance.getServices().GetStudentSchedule(AppVar.mStudent.getStudentId(),semesterid);
        listCall.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                if(!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse: Get Schedule failed");
                }
                else
                {
                    Log.d(TAG, "onResponse: Get Schedule Success");
                    List<Schedule> scheduleList = response.body();
                    listSchedule.setValue(scheduleList);
                }

            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
            }
        });
    }
    public void goBack()
    {
        getNavigator().goBack();
    }
}
