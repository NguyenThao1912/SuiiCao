package com.monsun.suiicao.views.login;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.security.HashMd5;
import com.monsun.suiicao.views.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends BaseViewModel<ILoginHandler> {
    private static final String TAG = "loginmodel";
    private static int LOGIN_FIREBASE_RESULT = 0;
    private static int GET_INFORMATION_RESULT = 0;
    private static String DEFAULT_FIREBASE_PASSWORD = "#$!@123987";
    //binding data in xml
    public String Username = "";
    public String Password = "";
    private ApiInstance caller = new ApiInstance();
    private MutableLiveData<Users> userLiveData ;
    private FirebaseAuth mAuth;
    private FirebaseUser FireAuth_User;
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
        mAuth = FirebaseAuth.getInstance();
        FireAuth_User = mAuth.getCurrentUser();
        if(FireAuth_User != null){
            mAuth.signOut();
            FireAuth_User = null;
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
                            // TODO: Student Login
                            if (!Username.startsWith("GV"))
                            {
                                getNavigator().showToast("Login Successful");
                                Log.d(TAG, "Login Successfull");
                                GetUserInformation();
                                getNavigator().startMainActivity();
                            }
                            // TODO : Mentor Login
                            else
                            {
                                //Mentor login
                            }
                        }
                        else
                        {
                            getNavigator().showToast("Wrong account or password");
                            Log.d(TAG, "onResponse: Login failed");
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
    public void onClickForgetPassword()
    {
        getNavigator().showToast("Please contact to your mentor for your problem ! ");
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
                       if (response.body() != null)
                       {
                           AppVar.currentuser = response.body();
                           AppVar.currentuser.setUsername(Username);
                           TRY_LOGGING_IN(AppVar.currentuser.getEmail());
                           GET_INFORMATION_RESULT = CommonUtils.GET_INFORMATION_SUCESS;
                       }
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
    private void TRY_LOGGING_IN(String email) {
        int k = SignUpUser_with_email_password(email, DEFAULT_FIREBASE_PASSWORD);
        if (k == CommonUtils.FIREBASE_CREATE_SUCCESS)
        {
            // TODO do nothing
        }
        else if (k == CommonUtils.FIREBASE_EMAIL_EXIST)
        {
            SignInUser_with_email_password(email,DEFAULT_FIREBASE_PASSWORD);
        }
        else
        {
            SignInUser_with_email_password(email,DEFAULT_FIREBASE_PASSWORD);
        }
    }
    private int SignUpUser_with_email_password(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getNavigator(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try
                        {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FireAuth_User = mAuth.getCurrentUser();
                                LOGIN_FIREBASE_RESULT =  CommonUtils.FIREBASE_CREATE_SUCCESS;
                            }
                            else
                            {
                                throw task.getException();
                            }
                        }
                        catch (FirebaseAuthWeakPasswordException weakPassword)
                        {
                            Log.d(TAG, "onComplete: weak_password");

                            LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_PASSWORD_TOO_WEAK;
                        }
                        // if user enters wrong password.
                        catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                        {
                            Log.d(TAG, "onComplete: malformed_email");

                            // TODO: Take your action
                            LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_EMAIL_WRONG_FORMAT;
                        }
                        catch (FirebaseAuthUserCollisionException existEmail)
                        {
                            Log.d(TAG, "onComplete: exist_email");

                            LOGIN_FIREBASE_RESULT = CommonUtils.FIREBASE_EMAIL_EXIST;
                        }
                        catch (Exception e)
                        {
                            Log.d(TAG, "onComplete: dump shit" + e.getMessage());
                        }
                    }
                });
        return LOGIN_FIREBASE_RESULT;
    }
    private void SignInUser_with_email_password(String email,String password)
    {
        try
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) getNavigator(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                FireAuth_User = mAuth.getCurrentUser();
                                Log.d(TAG, "Sign In FireBase: Success ");
                            }

                        }
                    });
        }
        catch (Exception e)
        {
            Log.d(TAG, "SignUpUser_with_email_password: " + e.getMessage());
        }
    }
}

