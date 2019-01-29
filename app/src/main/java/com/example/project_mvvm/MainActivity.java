package com.example.project_mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project_mvvm.adapters.RecyclerViewAdapter;
import com.example.project_mvvm.models.NicePlace;
import com.example.project_mvvm.models.ResponseModel;
import com.example.project_mvvm.models.Result;
import com.example.project_mvvm.network.ApiServiceGenerator;
import com.example.project_mvvm.network.UserService;
import com.example.project_mvvm.viewmodels.MainActivityViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private RecyclerViewAdapter viewAdapter;
    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();
        mainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(@Nullable List<NicePlace> nicePlaces) {
                viewAdapter.notifyDataSetChanged();

            }
        });


        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isUpdating) {

                if (isUpdating!=null && isUpdating) {
                    showProgressBar();
                } else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getNicePlaces().getValue().size() - 1);
                }

            }
        });

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);


        recyclerView = findViewById(R.id.rv_items);
        viewAdapter = new RecyclerViewAdapter(this, mainActivityViewModel.getNicePlaces().getValue());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(viewAdapter);
        userService = ApiServiceGenerator.createService(UserService.class);


    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void addItems(View view) {
        showProgressBar();
        userService.getUsers().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                ResponseModel responseModel = response.body();
                assert responseModel != null;
                List<Result> results = responseModel.getResults();
                String name = results.get(0).getName().getFirst() + " " + results.get(0).getName().getLast();
                String url = results.get(0).getPicture().getMedium();
                mainActivityViewModel.addNewValue(new NicePlace(name, url));
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
