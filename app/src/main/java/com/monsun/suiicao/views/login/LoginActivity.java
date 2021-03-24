package com.monsun.suiicao.views.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.LoginActivityBinding;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.main.MainActivity;

public class LoginActivity extends BaseActivity implements ILoginHandler {
    private static final String TAG = "LoginActivity";
    private LoginActivityBinding loginBinding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.login_activity);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.setNavigator(this);
        loginBinding.setLifecycleOwner(this);
        loginBinding.setViewModel(viewModel);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public void HandlerError(Throwable throwable) {
        //hander error
    }

    @Override
    public boolean login() {
        hideKeyBoard();
        loginBinding.txtPassword.setErrorEnabled(false);
        loginBinding.txtUsername.setErrorEnabled(false);
        if(TextUtils.isEmpty(loginBinding.txtUsername.getEditText().getText().toString())) {
            loginBinding.txtUsername.setError("Không được bỏ trống trường này");
            loginBinding.txtUsername.getEditText().requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(loginBinding.txtPassword.getEditText().getText().toString())) {
            loginBinding.txtPassword.setError("Không được bỏ trống trường này");
            loginBinding.txtPassword.getEditText().requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void startMainActivity() {
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setIsLoading(Boolean isLoading) {
        if (isLoading)
            showLoading();
        else
            hideLoading();
    }

}
