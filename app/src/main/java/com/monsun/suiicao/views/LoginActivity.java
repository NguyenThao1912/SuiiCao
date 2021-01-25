package com.monsun.suiicao.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.Utils;
import com.monsun.suiicao.databinding.LoginBinding;
import com.monsun.suiicao.models.User;
import com.monsun.suiicao.repositories.Database;
import com.monsun.suiicao.viewmodels.LoginViewModel;

import java.util.Objects;
import java.util.Random;

public class LoginActivity extends AppCompatActivity implements ILoginHandler  {
    private LoginViewModel loginVM;
    private LoginBinding binding;
    private long backpresstimes; // the time user press back button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginVM = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.login);
        binding.setLifecycleOwner(this);
        binding.setILogin(loginVM);
        binding.setHandler(this::onClick);
        //Result after click login
        loginVM.getIsSuccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(loginVM.isLogin)
                {
                    //start new intent
                }
                Utils.showToast(LoginActivity.this,s);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backpresstimes + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Press back again to exit the program", Toast.LENGTH_SHORT).show();
        }
        backpresstimes = System.currentTimeMillis();
    }

    @Override
    public void onClick() {
        loginVM.Validation();
    }
}
