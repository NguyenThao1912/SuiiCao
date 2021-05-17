package com.monsun.suiicao.views.curriculum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityCurriculumBinding;
import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.List;

public class CurriculumActivity extends BaseActivity implements ICurriculum, OnQueryTextListener {
    private static final String TAG = "CurriculumActivity";
    private ActivityCurriculumBinding binding;
    private CurriculumViewModel viewModel;
    private List<Curriculum> currentCurriculum;
    private SearchView searchView;
    private CurriculumAdapter examAdapter;
    public static Intent newIntent(Context context) {
        return new Intent(context, CurriculumActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_curriculum);
        viewModel = new ViewModelProvider(this).get(CurriculumViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.curriculumToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewModel.getData().observe(this, new Observer<List<Curriculum>>() {
            @Override
            public void onChanged(List<Curriculum> curricula) {
                currentCurriculum = curricula;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CurriculumActivity.this,LinearLayoutManager.VERTICAL,false);
                binding.listLecture.setLayoutManager(linearLayoutManager);
                examAdapter = new CurriculumAdapter(currentCurriculum);
                binding.listLecture.setAdapter(examAdapter);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.curriculum_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.curriculum_search);
        searchView = (SearchView) searchItem.getActionView();
        // Configure the search info and add any event listeners...
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.curriculum_sort: {

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        examAdapter.getFilter().filter(newText);
        return false;
    }
}