package com.monsun.suiicao.views.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.CommonUtils;
import com.monsun.suiicao.firebase.FirebaseSer;
import com.monsun.suiicao.views.mentoraccount.MentorAccountFragment;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.chatting.ContactFragment;
import com.monsun.suiicao.views.homefragment.HomeFragment;
import com.monsun.suiicao.views.homementor.mentormain;
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
        if(AppVar.mStudent != null){
            AppVar.getListUnstudyCurriculum();
        }
        tryLogin(this);
        super.onStart();
    }
    public static void tryLogin(Activity context)
    {
        if (AppVar.mStudent != null){
            if (FirebaseSer.mAuth.getCurrentUser() == null)
                FirebaseSer.TRY_LOGGING_IN(AppVar.mStudent.getEmail(), context);
        }
        if (AppVar.mMentor != null){
            if (FirebaseSer.mAuth.getCurrentUser() == null)
                FirebaseSer.TRY_LOGGING_IN(AppVar.mMentor.getEmail(), context);
        }

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

                            case R.id.ask_answer:
                            {
                                selectedFragment = ContactFragment.newInstance();
                                Toast.makeText(MainActivity.this, "ask answer", Toast.LENGTH_SHORT).show();
                                break;
                            }

                            case R.id.user_account:
                                if (AppVar.mStudent != null){
                                    FirebaseSer.mAuth.signOut();
                                    selectedFragment = userAccountFrag.newInstance();
                                    Toast.makeText(MainActivity.this, "user account", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                else
                                {
                                    FirebaseSer.mAuth.signOut();
                                    selectedFragment = MentorAccountFragment.newInstance();
                                    Toast.makeText(MainActivity.this, "user account", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            default:
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
        super.onDestroy();
        /* FirebaseSer.mAuth.signOut();
        AppVar.mMentor = null;*/
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(CommonUtils.MY_PREFERENCE,MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(CommonUtils.MY_USER);
        edit.remove(CommonUtils.TYPE_USER);
        if (AppVar.mMentor != null){
            
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(AppVar.mStudent);
            edit.putString(CommonUtils.MY_USER,json);
            edit.putString(CommonUtils.TYPE_USER,"student");
        }
        edit.apply();


    }

}