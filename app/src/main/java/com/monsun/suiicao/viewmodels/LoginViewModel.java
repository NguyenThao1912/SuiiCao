package com.monsun.suiicao.viewmodels;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monsun.suiicao.models.User;
import com.monsun.suiicao.views.LoginActivity;

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

    public static boolean loginAuth(String username, String password){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("accounts");
        boolean result = false;
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User post = dataSnapshot.getValue(User.class);
                System.out.println(post);
                rootRef.child(username);S

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return result;
    }
}

