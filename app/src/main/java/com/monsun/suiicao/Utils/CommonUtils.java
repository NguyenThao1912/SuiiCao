package com.monsun.suiicao.Utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;

import com.monsun.suiicao.R;

public final class CommonUtils {
    private CommonUtils() {

    }
    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        //set layout of dialog
        progressDialog.setContentView(R.layout.progress_dialog);
        //run progress dialog infinite
        progressDialog.setIndeterminate(true);
        //cannot cancel it
        progressDialog.setCancelable(false);
        //cannot cancel when touch out side
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
