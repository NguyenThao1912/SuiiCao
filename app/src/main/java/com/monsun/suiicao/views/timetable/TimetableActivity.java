package com.monsun.suiicao.views.timetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.monsun.suiicao.R;
import com.monsun.suiicao.views.base.BaseActivity;

public class TimetableActivity extends BaseActivity {


    public static Intent newIntent(Context context) {
        return new Intent(context, TimetableActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}