package com.monsun.suiicao;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.monsun.suiicao.databinding.LoginBinding;
import com.monsun.suiicao.models.User;
import com.monsun.suiicao.viewmodels.LoginViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginVM;
    private LoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginVM = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.login);
        binding.setLifecycleOwner(this);
        binding.setILogin(loginVM);

        //check simple input realtime
        loginVM.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(TextUtils.isEmpty(Objects.requireNonNull(user).getUsername()))
                {
                    binding.username.setError("Username can not be empty");
                    binding.username.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword()))
                {
                    binding.password.setError("Password can not be empty");
                    binding.password.requestFocus();
                }
                else if (!user.isPasswordGreaterThan_Eight())
                {
                    binding.password.setError("Password must have at least 8 character");
                    binding.password.requestFocus();
                }
            }
        });
    }
}