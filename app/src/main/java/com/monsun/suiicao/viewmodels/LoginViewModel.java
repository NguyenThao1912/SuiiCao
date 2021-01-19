package com.monsun.suiicao.viewmodels;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.monsun.suiicao.models.User;

import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class LoginViewModel extends ViewModel {
    //binding data in xml
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public ObservableField<String> isSuccess = new ObservableField<>();
    //_user variable
    private MutableLiveData<User> _user;

    public LiveData<User> getUser() {
        if (_user == null) {
            _user = new MutableLiveData<>();
        }
        return _user;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {
        try {
            User u = new User(username.getValue(), password.getValue());
            _user.setValue(u);
        } catch (NullPointerException e) {
            Log.e("Thao", e.getMessage());
        }
    }
    //logic check on server here
}

