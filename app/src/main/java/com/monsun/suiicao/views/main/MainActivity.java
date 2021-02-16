package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.views.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private TextView Title;
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
    }
    private void setWidget()
    {
        Title = (TextView) findViewById(R.id.greeting);
        Title.setText("Xin Chào, " + AppVar.Currentuser.getFullName());
    }
}