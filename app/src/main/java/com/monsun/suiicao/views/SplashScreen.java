package com.monsun.suiicao.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.AppUtil;
import com.monsun.suiicao.views.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_DURATION = 3; // 4 seconds
    private Animation topAnimation,botAnimation;
    ImageView logo;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Animate();
        Postpone();
    }

    private void Animate() {
        //load Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //Hooks
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        //Set animation for View
        logo.setAnimation(topAnimation);
        slogan.setAnimation(botAnimation);
    }

    private void Postpone() {
        if (AppUtil.isNetWorkAvaiable(this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //do something

                    //Open Login activity
                    Intent intent = new Intent(SplashScreen.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DURATION * 1000);
        }
        else {
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_LONG).show();
        }
    }

}