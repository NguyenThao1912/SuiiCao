package com.monsun.suiicao.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.monsun.suiicao.MainActivity;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.AppUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loaddata();
    }

    private void loaddata() {
        if (AppUtil.isNetWorkAvaiable(this))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //do something on server

                    //open main screen

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            },3000);
        }
        else {
            Toast.makeText(this,"Network disconnected",Toast.LENGTH_LONG).show();
        }
    }


}