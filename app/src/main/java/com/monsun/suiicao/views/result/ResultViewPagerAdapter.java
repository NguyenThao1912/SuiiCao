package com.monsun.suiicao.views.result;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.monsun.suiicao.models.Semester;
import com.monsun.suiicao.repositories.ApiInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultViewPagerAdapter extends FragmentPagerAdapter {
    private int studentid;
    private List<Semester> list = new ArrayList<>();
    public ResultViewPagerAdapter(@NonNull FragmentManager fm, int studentid, List<Semester> list) {
        super(fm);
        this.studentid = studentid;
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new ResultFragment(list.get(position).getSemesterKey(),studentid);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        for (int i = 0; i <list.size(); i++) {
        if(position==i){
            return list.get(i).getSemesterName();
        }
    }
        return null;
    }


}
