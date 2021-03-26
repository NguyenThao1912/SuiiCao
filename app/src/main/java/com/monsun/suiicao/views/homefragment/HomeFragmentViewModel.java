package com.monsun.suiicao.views.homefragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.views.base.BaseViewModel;

public class HomeFragmentViewModel extends BaseViewModel<IHomeHandler> {
    private static final String TAG = "HomeFragmentViewModel";
    public MutableLiveData<String> greeting = new MutableLiveData<>();
    public HomeFragmentViewModel()
    {
        Log.d(TAG, "HomeFragmentViewModel: ");
        greeting.setValue("Xin Chào" + AppVar.mStudent.getFullName());
    }
    public void _OnClick_Student_Exam()
    {
        getNavigator().OpenStudentExam();
    }
    public void _OnClick_Student_Schedule()
    {
        getNavigator().OpenSchedule();
    }
    public void _OnClick_Student_Curriculum()
    {
        getNavigator().OpenCurriculum();
    }
}
