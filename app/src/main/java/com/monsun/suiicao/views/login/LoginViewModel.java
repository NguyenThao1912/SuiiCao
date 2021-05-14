package com.monsun.suiicao.views.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.models.Mentor;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.security.HashMd5;
import com.monsun.suiicao.views.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends BaseViewModel<ILoginHandler> {
    private static final String TAG = "loginmodel";

    private static int GET_INFORMATION_RESULT = 0;

    //binding data in xml
    public String Username = "";
    public String Password = "";
    private ApiInstance caller = new ApiInstance();
    private MutableLiveData<Users> userLiveData ;

    public LiveData<Users> getUser() {
        if (userLiveData == null)
            userLiveData = new MutableLiveData<>();
        return userLiveData;
    }

    public LoginViewModel() {
        super();
    }

    public void onClickLogin()
    {
        FirebaseUser user = FirebaseSer.mAuth.getCurrentUser();
        if( user != null){
            FirebaseSer.mAuth.signOut();
            user = null;
        }
        if (getNavigator().login())
        {
            getNavigator().setIsLoading(true);
            Call<Boolean> call =  caller.getServices().GetLoginResult(Username, HashMd5.getMd5(Password));
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (!response.isSuccessful())
                    {
                       getNavigator().showToast("Code :" + response.code());
                    }
                    try
                    {
                        if (response.body())
                        {

                            if (!Username.startsWith("GV"))
                            {
                                getNavigator().showToast("Login Successful");
                                Log.d(TAG, "Login Successfull");
                                GetUserInformation();

                            }
                            // TODO : Mentor Login
                            else
                            {
                                getNavigator().showToast("Login Successful");
                                Log.d(TAG, "Login Successfull");
                                GetMentorInfomation();
                            }
                        }
                        else
                        {
                            getNavigator().showToast("Wrong account or password");
                            Log.i(TAG, "onResponse: Login failed");
                        }


                    }
                    catch (Exception e)
                    {
                        Log.d(TAG, "onResponse: " + e.getMessage());
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

    private void Open_Main()
    {
        getNavigator().startMainActivity();
    }
    public void onClickForgetPassword()
    {
        getNavigator().showToast("Please contact to your mentor for your problem ! ");
    }
    private void GetMentorInfomation()
    {
        Call<Mentor> call = caller.getServices().getMentorByUserName(Username);
        call.enqueue(new Callback<Mentor>() {
            @Override
            public void onResponse(Call<Mentor> call, Response<Mentor> response) {
                if (response.isSuccessful())
                {
                    try{

                        AppVar.mMentor = response.body();
                        AppVar.mMentor.setUsername(Username);
                        Log.d(TAG, "onResponse: Get Data" + AppVar.mMentor.getMentorName());
                        GET_INFORMATION_RESULT = CommonUtils.GET_INFORMATION_SUCESS;
                        // TODO start main activity
                        Open_Main();

                    }
                    catch (Exception e)
                    {

                    }
                }
            }

            @Override
            public void onFailure(Call<Mentor> call, Throwable t) {

            }
        });
    }
    private void GetUserInformation()
    {
        Call<Users> call =  caller.getServices().getStudentbyUsername(Username);
        call.enqueue(new Callback<Users>() {
           @Override
           public void onResponse(Call<Users> call, Response<Users> response) {
               if (response.isSuccessful())
               {
                   try{

                           AppVar.mStudent = response.body();
                           AppVar.mStudent.setUsername(Username);
                           Log.d(TAG, "onResponse: Get Data" + AppVar.mStudent.getFullName());
                           GET_INFORMATION_RESULT = CommonUtils.GET_INFORMATION_SUCESS;
                           // TODO start main activity
                       Open_Main();

                   }
                   catch (Exception e)
                   {

                   }
               }
           }
           @Override
           public void onFailure(Call<Users> call, Throwable t) {
               Log.d(TAG, "onFailure: get user information" + t.getMessage());
           }
        });

    }

}

