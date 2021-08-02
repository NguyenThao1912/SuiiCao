package com.monsun.suiicao.views.mentoraccount;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.views.base.BaseViewModel;
import com.monsun.suiicao.views.mentoraccount.IUserHandler;

public class UserViewModel extends BaseViewModel<IUserHandler> {
    public MutableLiveData<String> men_fullName;
    public MutableLiveData<String> menID;

    public UserViewModel()
    {
        men_fullName = new MutableLiveData<>();
        menID = new MutableLiveData<>();
        initdata();
    }

    private void initdata() {
            if (AppVar.mMentor.getMentorName() != null)
                men_fullName.setValue(AppVar.mMentor.getMentorName());
            if (AppVar.mMentor.getUsername() != null)
                menID .setValue(AppVar.mMentor.getUsername());

    }

    public void signOutClick()
    {
        Log.d("MentorAccountFrag", "signOutClick: ");
        getNavigator().OpenLoginActivity();
        AppVar.mMentor = null;
        AppVar.mStudent = null;
    }
    public void MentorInforClick()
    {
        Log.d("MentorAccountFrag", "MentorInforClick: ");
        getNavigator().OpenMentorInfor();
    }
    public void ImagePickerClick(){
        getNavigator().OpenUploadImage();
    }
}
