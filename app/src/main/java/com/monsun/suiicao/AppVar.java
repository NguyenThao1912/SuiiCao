package com.monsun.suiicao;

import android.app.Application;
import android.util.Log;

import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.models.Mentor;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.repositories.ApiInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppVar extends Application {
    private static final String TAG = "AppVar";
    public static Users mStudent;
    public static Mentor mMentor;
    public static Semester semester = new Semester(7,"2_2021_2022");
    public static List<Curriculum> unstudy_curriculum;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: APPVAR");

    }
    public static void getListUnstudyCurriculum(){
        ApiInstance apiInstance = new ApiInstance();
        Call<List<Curriculum>> call = apiInstance.getServices().getUnstudylecture(AppVar.mStudent.getStudentId(), Integer.parseInt(AppVar.mStudent.getClassId()));
        call.enqueue(new Callback<List<Curriculum>>() {
            @Override
            public void onResponse(Call<List<Curriculum>> call, Response<List<Curriculum>> response) {
                unstudy_curriculum = response.body();
            }

            @Override
            public void onFailure(Call<List<Curriculum>> call, Throwable t) {

            }
        });
    }

}
