package com.monsun.suiicao.views.useraccount;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.views.base.BaseViewModel;

public class UserViewModel extends BaseViewModel<IUserHandler> {
    public MutableLiveData<String> stu_fullName;
    public MutableLiveData<String> stuID;
    public MutableLiveData<String> major_class_year;

    public UserViewModel()
    {
        stu_fullName = new MutableLiveData<>();
        stuID = new MutableLiveData<>();
        major_class_year = new MutableLiveData<>();
        initdata();
    }

    private void initdata() {
        if (AppVar.currentuser.getFullName() != null)
            stu_fullName.setValue(AppVar.currentuser.getFullName());
        if (AppVar.currentuser.getYearIn() != null)
            major_class_year.setValue(AppVar.currentuser.getYearIn());
        if (AppVar.currentuser.getUsername() != null)
            stuID .setValue(AppVar.currentuser.getUsername()); ;
    }

    public void signOutClick()
    {
        Log.d("userAccountFrag", "signOutClick: ");
        getNavigator().OpenLoginActivity();
    }
    public void stuInforClick()
    {
        Log.d("userAccountFrag", "OpenStudentInformation: ");
        getNavigator().OpenStudentInfor();
    }
}
