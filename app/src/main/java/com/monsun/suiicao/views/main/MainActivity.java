package com.monsun.suiicao.views.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.Utils.UtilitiesGridAdapter;
import com.monsun.suiicao.models.Utilities;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
        List<Utilities> image_details = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new UtilitiesGridAdapter(image_details, this));

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Utilities utilities = (Utilities) o;
                Toast.makeText(MainActivity.this, "Selected :"
                        + " " + utilities, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void setWidget()
    {
        Title = (TextView) findViewById(R.id.greeting);
        Title.setText("Xin Chào, " + AppVar.Currentuser.getFullName());
    }

    private  List<Utilities> getListData() {
        List<Utilities> list = new ArrayList<Utilities>();
        Utilities cn1 = new Utilities("cn1", "cn1");
        Utilities cn2 = new Utilities("cn2", "cn2");
        Utilities cn3 = new Utilities("cn3", "cn3");
        Utilities cn4 = new Utilities("cn4", "cn4");
        Utilities cn5 = new Utilities("cn5", "cn5");

        list.add(cn1);
        list.add(cn2);
        list.add(cn3);
        list.add(cn4);
        list.add(cn5);

        return list;
    }
}