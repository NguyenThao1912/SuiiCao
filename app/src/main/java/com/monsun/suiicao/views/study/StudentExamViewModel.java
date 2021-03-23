package com.monsun.suiicao.views.study;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.models.CourseExam;
import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentExamViewModel extends BaseViewModel<IStudentExam> {
    private static final String TAG = "StudentExamViewModel";
    private MutableLiveData<List<CourseExam>> examlist =  new MutableLiveData<>();
    private MutableLiveData<List<Semester>> listSemester = new MutableLiveData<>();
    private ApiInstance apiInstance;
    public StudentExamViewModel()
    {
        apiInstance = new ApiInstance();
        setData();
    }
    public MutableLiveData<List<Semester>> getListSemester() {
        return listSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        GetListExam(currentSemester);
    }
    public MutableLiveData<List<CourseExam>> getExamlist() {
        return examlist;
    }

    private void GetListExam(Semester se)
    {
        //Đang test nên để mặc định id sinh viên là 1 - id nó là kiểu int nhé

        Call<List<CourseExam>> listCall = apiInstance.getServices().getExamList(1,se.getSemesterKey());
        listCall.enqueue(new Callback<List<CourseExam>>() {
            @Override
            public void onResponse(Call<List<CourseExam>> call, Response<List<CourseExam>> response) {
                if(!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse: Get CourseExam failed");
                    examlist.setValue(null);
                }
                else
                {
                    List<CourseExam> courseExams = response.body();
                    examlist.setValue(courseExams);
                }
            }

            @Override
            public void onFailure(Call<List<CourseExam>> call, Throwable t) {

            }
        });
    }
    public void setData()
    {
        Call<List<Semester>> lsemester = apiInstance.getServices().getSemester();
        lsemester.enqueue(new Callback<List<Semester>>() {
            @Override
            public void onResponse(Call<List<Semester>> call, Response<List<Semester>> response) {
                if(!response.isSuccessful())
                {
                    Log.d(TAG, "onResponse: Get semester failed");
                }
                else
                {
                    List<Semester> s = response.body();
                    s.add(new Semester("","- Chọn Kỳ -"));
                    listSemester.setValue(s);
                }
            }

            @Override
            public void onFailure(Call<List<Semester>> call, Throwable t) {
                Log.d(TAG, "onResponse: Get semester failed " + t.getMessage() );
            }
        });
    }
    public void goBack()
    {
        getNavigator().goBack();
    }
}
