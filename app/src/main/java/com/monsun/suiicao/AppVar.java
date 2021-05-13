package com.monsun.suiicao;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.monsun.suiicao.models.Mentor;
import com.monsun.suiicao.models.Users;

public class AppVar extends Application {
    public static Users mStudent;
    public static Mentor mMentor;

    @Override
    public void onCreate() {

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        super.onCreate();
    }
}
