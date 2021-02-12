package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.monsun.suiicao.R;

public class MainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}