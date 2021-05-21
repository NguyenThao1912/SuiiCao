package com.monsun.suiicao;

import android.app.Application;
import android.util.Log;

import com.monsun.suiicao.models.Mentor;
import com.monsun.suiicao.models.Users;

public class AppVar extends Application {
    private static final String TAG = "AppVar";
    public static Users mStudent;
    public static Mentor mMentor;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: APPVAR");

    }
}
