package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.UtilitiesGridAdapter;
import com.monsun.suiicao.models.Utilities;
import com.monsun.suiicao.views.base.BaseActivity;
import com.monsun.suiicao.views.useraccount.userAccountFrag;

import java.util.List;

public class MainActivity extends BaseActivity implements UtilitiesGridAdapter.onItemListener,IMainHandler{
    private TextView Title;
    private RecyclerView menu;
    private UtilitiesGridAdapter adapter;
    private MainViewModel mainViewModel;
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment;
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
        adapter = new UtilitiesGridAdapter(this,this);
        menu.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false) ;
        mainViewModel.getListitem().observe(this, new Observer<List<Utilities>>() {
            @Override
            public void onChanged(List<Utilities> utilities) {
                adapter.setList(utilities);
                menu.setLayoutManager(gridLayoutManager);
                menu.setAdapter(adapter);
            }
        });

    }
    private void setWidget()
    {
        bottomNavigationView =  findViewById(R.id.bottom_navigation);
        menu = findViewById(R.id.menu_stu);
        Title = (TextView) findViewById(R.id.greeting);
        if (AppVar.Currentuser != null )
            Title.setText("Xin Chào, " + AppVar.Currentuser.getFullName());
    }
    private void setCLickNavBar()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.home_Screen:
                        Toast.makeText(MainActivity.this, "Home screen", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.help:
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ask_answer:
                        Toast.makeText(MainActivity.this, "ask answer", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.user_account:
                        selectedFragment = new userAccountFrag();
                        Toast.makeText(MainActivity.this, "user account", Toast.LENGTH_SHORT).show();


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });
    }
    @Override
    public void onMenuItemClick(int position) {
        switch (position)
        {
            case 0:
                Toast.makeText(this, "Học Vụ", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Thời Khóa Biểu", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Lịch Thi", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    @Override
    public void OpenLoginActivity() {

    }

    @Override
    public void HandlerError() {

    }

    @Override
    public void OpenUserAccount() {
        Intent i = new Intent(this, userAccountFrag.class);
        startActivity(i);
    }

}