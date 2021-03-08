package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.monsun.suiicao.views.homefragment.HomeFragment;
import com.monsun.suiicao.R;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.useraccount.userAccountFrag;

public class MainActivity extends BaseActivity implements IMainHandler{
    private MainViewModel mainViewModel;
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener nav;
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
        setCLickNavBar();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.setNavigator(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }
    private void setWidget()
    {
        bottomNavigationView =  findViewById(R.id.bottom_navigation);

    }
    private void setCLickNavBar()
    {
        nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectedFragment = null;
                        switch (item.getItemId())
                        {
                            case R.id.home_Screen:
                                selectedFragment = HomeFragment.newInstance();
                                Toast.makeText(MainActivity.this, "Home screen", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.help:
                                Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                                return false;

                            case R.id.ask_answer:
                                Toast.makeText(MainActivity.this, "ask answer", Toast.LENGTH_SHORT).show();
                                return false;

                            case R.id.user_account:
                                selectedFragment = userAccountFrag.newInstance();
                                Toast.makeText(MainActivity.this, "user account", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                        return true;
                    }
                };

    }

    @Override
    public void OpenLoginActivity() {

    }

    @Override
    public void HandlerError() {

    }


}