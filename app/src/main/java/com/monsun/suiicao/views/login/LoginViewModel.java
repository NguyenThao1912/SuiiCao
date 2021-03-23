package com.monsun.suiicao.views.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.models.User;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends BaseViewModel<ILoginHandler> {
    private static final String TAG = "t";
    //binding data in xml
    public String Username = "";
    public String Password = "";
    private ApiInstance caller = new ApiInstance();
    private MutableLiveData<User> userLiveData ;
    public LiveData<User> getUser() {
        if (userLiveData == null)
            userLiveData = new MutableLiveData<>();
        return userLiveData;
    }

    public LoginViewModel() {
        super();
    }

    public void onClickLogin()
    {
        if (getNavigator().login())
        {
            getNavigator().setIsLoading(true);
            Call<Boolean> call =  caller.getServices().GetLoginResult(Username, Password);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (!response.isSuccessful())
                    {
                       getNavigator().showToast("Code :" + response.code());
                    }
                    if (response.body() == true)
                    {
                        getNavigator().showToast("Login successful");
                        Log.d(TAG, "Login Successfull");
                        getNavigator().startMainActivity();
                    }
                    else
                    {
                        getNavigator().showToast("Wrong account or password");
                        Log.d(TAG, "onResponse: Login failed");
                    }
                       
                    
                    getNavigator().setIsLoading(false);
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Log.d(TAG, "onFailure: " +  t.getMessage());
                    getNavigator().setIsLoading(false);
                }
            });
        }

    }
    public void onClickForgetPassword()
    {
        getNavigator().showToast("Please contact to your mentor for your problem ! ");
    }

}

