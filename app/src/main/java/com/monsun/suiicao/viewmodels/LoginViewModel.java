package com.monsun.suiicao.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.models.User;


public class LoginViewModel extends ViewModel {
    //binding data in xml
    public String Username = "";
    public String Password = "";
    private MutableLiveData<String> isSuccess = new MutableLiveData<>() ;
    public boolean isLogin = false;

    public LiveData<String> getIsSuccess() {
        if (isSuccess == null) isSuccess= new MutableLiveData<>();
        return isSuccess;
    }

    public void Validation()
    {
        if (Username.isEmpty())
        {
            isSuccess.setValue("Invalid username");
            return ;
        }
        if (Password.isEmpty())
        {
            isSuccess.setValue("Invalid Password");
            return ;
        }
        if (Password.length()<8)
        {
            isSuccess.setValue("Password must have at least 8 characters");
            return ;
        }
        loginAuth();
    }
    public boolean loginAuth() {

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("accounts");
        User loginUser = new User(Username, Password);
        rootRef.orderByChild("accounts");

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.getKey().equals(loginUser.getUsername())) {
                        if (data.child("password").getValue().equals(loginUser.getPassword()))
                        {
                            // Log.d("id",data.child("username").getValue().toString() + " " + data.child("password").getValue().toString());
                            isLogin = true;
                            isSuccess.setValue("Login Success");
                            break;
                        }
                    }
                }
                if(!isLogin)
                    isSuccess.setValue("Failed To Login !!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return isLogin;
    }
}

