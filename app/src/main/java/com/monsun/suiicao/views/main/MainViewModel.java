package com.monsun.suiicao.views.main;

import androidx.lifecycle.MutableLiveData;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Utilities;
import com.monsun.suiicao.views.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends BaseViewModel<IMainHandler> {
    private MutableLiveData<List<Utilities>> listitem;
    private List<Utilities> utilities;
    public MainViewModel ()
    {
        listitem = new MutableLiveData<>();
        initData();
    }

    private void initData() {
        utilities = new ArrayList<>();
        utilities.add(new Utilities(R.drawable.openbook, "Học vụ"));
        utilities.add(new Utilities(R.drawable.calendar_item, "Thời Khóa Biểu"));
        utilities.add(new Utilities(R.drawable.calendar_item, "Lịch Thi"));
        utilities.add(new Utilities(R.drawable.calendar_item, "Giáo Trình"));
        listitem.setValue(utilities);
    }


}
