package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.UtilitiesGridAdapter;
import com.monsun.suiicao.models.Utilities;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private TextView Title;
    private RecyclerView menu;
    private List<Utilities> listitem;
    private UtilitiesGridAdapter adapter;
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
        List<Utilities> image_details = getListData();
        menu = findViewById(R.id.menu_stu);
        adapter = new UtilitiesGridAdapter(this,image_details);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false) ;
        menu.setLayoutManager(gridLayoutManager);
        menu.setAdapter(adapter);
        // When the user clicks on the GridItem

    }
    private void setWidget()
    {
        Title = (TextView) findViewById(R.id.greeting);
        Title.setText("Xin Chào, " + AppVar.Currentuser.getFullName());
    }

    private  List<Utilities> getListData() {
        List<Utilities> list = new ArrayList<Utilities>();
        Utilities cn1 = new Utilities(R.drawable.book, "Học vụ");
        Utilities cn2 = new Utilities(R.drawable.book, "Thời Khóa Biểu");
        Utilities cn3 = new Utilities(R.drawable.book, "menu_item_3");
        Utilities cn4 = new Utilities(R.drawable.book, "menu_item_4");
        Utilities cn5 = new Utilities(R.drawable.book, "menu_item_5");

        list.add(cn1);
        list.add(cn2);
        list.add(cn3);
        list.add(cn4);
        list.add(cn5);

        return list;
    }
}