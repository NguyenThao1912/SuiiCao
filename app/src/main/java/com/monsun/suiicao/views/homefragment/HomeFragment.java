package com.monsun.suiicao.views.homefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.views.base.BaseFragment;


public class HomeFragment extends BaseFragment {

    private CardView study_summary,calender;
    private TextView greeting;
    public View v;
    public HomeFragment() {
        // Required empty public constructor

    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    private void setWiget()
    {
        greeting = v.findViewById(R.id.greeting);
        study_summary = v.findViewById(R.id.feature_summary_score);
        calender = v.findViewById(R.id.feature_2);
        if (AppVar.Currentuser != null)
        {
            greeting.setText("Xin Chào " + AppVar.Currentuser.getFullName());
        }
        study_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "hoc vu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        setWiget();
        return v;
    }
}