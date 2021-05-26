package com.monsun.suiicao.views.curriculum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.monsun.suiicao.AppVar;
import com.monsun.suiicao.R;
import com.monsun.suiicao.databinding.ActivityCurriculumBinding;
import com.monsun.suiicao.models.Curriculum;
import com.monsun.suiicao.repositories.ApiInstance;
import com.monsun.suiicao.views.base.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurriculumActivity extends BaseActivity implements ICurriculum, OnQueryTextListener {
    private static final String TAG = "CurriculumActivity";
    private ActivityCurriculumBinding binding;
    private CurriculumViewModel viewModel;
    private List<Curriculum> currentCurriculum;
    private List<Curriculum> unstudycurriculum;
    private SearchView searchView;
    private CurriculumAdapter examAdapter;
    public static Intent newIntent(Context context) {
        return new Intent(context, CurriculumActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_curriculum);
        viewModel = new ViewModelProvider(this).get(CurriculumViewModel.class);
        viewModel.setNavigator(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.curriculumToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Chương trình học");
        GetListUnstudylecture();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (searchView.isIconified())
            searchView.setIconified(false);
        else
             finish();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        examAdapter.getFilter().filter(query);return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        examAdapter.getFilter().filter(newText);
        return false;
    }
    private void GetListUnstudylecture()
    {
        ApiInstance apiInstance = new ApiInstance();
        Call<List<Curriculum>> call = apiInstance.getServices().getUnstudylecture(AppVar.mStudent.getStudentId(), Integer.parseInt(AppVar.mStudent.getClassId()));
        call.enqueue(new Callback<List<Curriculum>>() {
            @Override
            public void onResponse(Call<List<Curriculum>> call, Response<List<Curriculum>> response) {
                unstudycurriculum = response.body();
                viewModel.getData().observe(CurriculumActivity.this, curricula -> {
                    currentCurriculum = curricula;
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CurriculumActivity.this,LinearLayoutManager.VERTICAL,false);
                    binding.listLecture.setLayoutManager(linearLayoutManager);
                    examAdapter = new CurriculumAdapter(currentCurriculum,unstudycurriculum);
                    binding.listLecture.setAdapter(examAdapter);
                });
            }

            @Override
            public void onFailure(Call<List<Curriculum>> call, Throwable t) {

            }
        });
    }
}