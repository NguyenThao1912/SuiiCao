package com.monsun.suiicao.views.homementor;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.views.base.BaseViewModel;

public class MentormainViewModel extends BaseViewModel<IMentormain> {
    public MutableLiveData<String> greeting = new MutableLiveData<>();
    private static final String TAG = "mentormainViewModel";
    public MentormainViewModel()
    {
        Log.d(TAG, "mentormainViewModel: ");
        greeting.setValue("Xin Chào " + AppVar.mMentor.getMentorName());
    }
    public void OpenListStudent()
    {
        getNavigator().OpenListStudent();
    }
}
