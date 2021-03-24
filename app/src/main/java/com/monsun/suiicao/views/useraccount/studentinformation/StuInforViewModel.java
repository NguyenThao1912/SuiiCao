package com.monsun.suiicao.views.useraccount.studentinformation;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.views.base.BaseViewModel;

public class StuInforViewModel extends BaseViewModel<IStuInformation> {

    public MutableLiveData<Users> getInfor() {
        return infor;
    }
    public MutableLiveData<String> Titletoolbar = new MutableLiveData();
    MutableLiveData<Users> infor = new MutableLiveData<>();
    public StuInforViewModel()
    {
        setData();
    }
    private void setData()
    {
        Titletoolbar.setValue("Thông tin sinh viên");
        infor.setValue(AppVar.currentuser);

    }
}
