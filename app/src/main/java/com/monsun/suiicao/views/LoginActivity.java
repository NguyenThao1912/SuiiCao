package com.monsun.suiicao.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.LoginBinding;
import com.monsun.suiicao.models.User;
import com.monsun.suiicao.viewmodels.LoginViewModel;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
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

        //check simple input realtime
        loginVM.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (TextUtils.isEmpty(Objects.requireNonNull(user).getUsername())) {
                    binding.username.setError("Username can not be empty");
                    binding.username.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                    binding.password.setError("Password can not be empty");
                    binding.password.requestFocus();
                } else if (!user.isPasswordGreaterThan_Eight()) {
                    binding.password.setError("Password must have at least 8 character");
                    binding.password.requestFocus();
                }
                else{
                    //check user and password if true
                    if(binding.username.getText().equals("thao") && binding.password.getText().equals("12345678"))
                    {
                        //do something
                        loginVM.isSuccess.set("Success");
                    }
                    else
                        //give them a message
                        loginVM.isSuccess.set("Failed");
                }
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
}
