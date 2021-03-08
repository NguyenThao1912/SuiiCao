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
        stu_fullName.setValue(AppVar.Currentuser.getFullName());
        major_class_year.setValue(AppVar.Currentuser.getmajor_class_year());
        stuID .setValue(AppVar.Currentuser.getUsername()); ;
    }

    public void signOutClick()
    {
        Log.d("userAccountFrag", "signOutClick: ");
        getNavigator().OpenLoginActivity();
    }
}
