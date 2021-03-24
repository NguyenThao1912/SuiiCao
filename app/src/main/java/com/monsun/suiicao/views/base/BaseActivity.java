package com.monsun.suiicao.views.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.monsun.suiicao.Utils.CommonUtils;

public abstract class BaseActivity extends AppCompatActivity   {
    private ProgressDialog mProgressDialog;
    private long backpresstimes; // timer check user press back button

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public Boolean HasPermission(String permission)
    {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected void hideKeyBoard()
    {
        View view = getCurrentFocus();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null){
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        }
    }
    public void hideLoading()
    {
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.cancel();
        }
    }
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
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
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }
}
