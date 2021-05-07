package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.chatting.ContactFragment;
import com.monsun.suiicao.views.homefragment.HomeFragment;
import com.monsun.suiicao.views.homementor.mentormain;
import com.monsun.suiicao.views.login.LoginActivity;
import com.monsun.suiicao.views.useraccount.userAccountFrag;

public class MainActivity extends BaseActivity implements IMainHandler{
    private static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener nav;
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onStart() {
        if (AppVar.mStudent != null){
            if (FirebaseSer.FireAuth_User == null)
                FirebaseSer.TRY_LOGGING_IN(AppVar.mStudent.getEmail(), MainActivity.this);
        }
        if (AppVar.mMentor != null){
            if (FirebaseSer.FireAuth_User == null)
                FirebaseSer.TRY_LOGGING_IN(AppVar.mMentor.getEmail(), MainActivity.this);
        }
        super.onStart();
    }

    private void reload() {
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
        setCLickNavBar();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.setNavigator(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);

        if (AppVar.mMentor != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new mentormain()).commit();
        else
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
                            {
                                if (AppVar.mStudent != null){
                                    selectedFragment = HomeFragment.newInstance();
                                    Toast.makeText(MainActivity.this, "Home screen", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                else
                                {
                                    selectedFragment = mentormain.newInstance();
                                    Toast.makeText(MainActivity.this, "mentor main", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }


                            case R.id.help:
                                Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                                return false;

                            case R.id.ask_answer:
                                selectedFragment = ContactFragment.newInstance();
                                Toast.makeText(MainActivity.this, "ask answer", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.user_account:
                                if (AppVar.mStudent != null){
                                    selectedFragment = userAccountFrag.newInstance();
                                    Toast.makeText(MainActivity.this, "user account", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                else
                                {
                                    FirebaseSer.mAuth.signOut();
                                    FirebaseSer.FireAuth_User = null;
                                    AppVar.mMentor = null;
                                    Intent t = LoginActivity.newIntent(MainActivity.this);
                                    startActivity(t);
                                    finish();
                                }

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

    @Override
    protected void onDestroy() {

        FirebaseSer.mAuth.signOut();
        FirebaseSer.FireAuth_User = null;
        AppVar.mMentor = null;

        super.onDestroy();
    }
}