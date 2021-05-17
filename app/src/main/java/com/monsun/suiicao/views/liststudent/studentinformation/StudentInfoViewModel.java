package com.monsun.suiicao.views.liststudent.studentinformation;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.views.base.BaseViewModel;

public class StudentInfoViewModel extends BaseViewModel<IStudentinfo> {
    public MutableLiveData<Users> usersMutableLiveData = new MutableLiveData<>();

    public StudentInfoViewModel() {

    }

}
