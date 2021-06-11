package com.monsun.suiicao.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.AppUtil;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.databinding.DialogNoConnectionBinding;
import com.monsun.suiicao.models.Users;
import com.monsun.suiicao.views.login.LoginActivity;
import com.monsun.suiicao.views.main.MainActivity;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener{
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
            new Handler().postDelayed(() -> {
                //do something
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(CommonUtils.MY_PREFERENCE,MODE_PRIVATE);
                String type = preferences.getString(CommonUtils.TYPE_USER,"");
                if (type != null && !type.isEmpty()){
                    if (type.equals("mentor"))
                    {

                    }
                    else {
                        Gson gson = new Gson();
                        String json = preferences.getString(CommonUtils.MY_USER, "");
                        AppVar.mStudent =  gson.fromJson(json, Users.class);
                    }
                    Intent intent = MainActivity.newIntent(SplashScreen.this);
                    startActivity(intent);
                    finish();
                }
                else {
                    //Open Login activity
                    Intent intent = new Intent(SplashScreen.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }, SPLASH_DURATION * 1000);
        }
        else {
            showDialogNoConnection(this);
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_LONG).show();
        }
    }

    private void showDialogNoConnection(Context context) {
        Dialog dialog = new Dialog(context);
        DialogNoConnectionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_no_connection, null, false);
        dialog.setContentView(binding.getRoot());
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        binding.tryAgain.setOnClickListener(this);
        binding.exit.setOnClickListener(this);
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.try_again:{
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
            case R.id.exit:{
                finish();
            }
        }
    }
}