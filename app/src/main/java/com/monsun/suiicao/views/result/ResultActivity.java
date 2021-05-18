package com.monsun.suiicao.views.result;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityTongKetBinding;

public class ResultActivity extends AppCompatActivity {
    private ActivityTongKetBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tong_ket);
        binding.viewPager.setAdapter();
    }
}
