package com.monsun.suiicao.views.studentresult;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.monsun.suiicao.models.Semester;

import java.util.List;

public class StudentResultViewPager_Adapter extends FragmentStateAdapter {

    public StudentResultViewPager_Adapter(@NonNull Fragment fragment, List<Semester> semesters) {
        super(fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
