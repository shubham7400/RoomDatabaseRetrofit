package com.example.roomdatabaseretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.roomdatabaseretrofit.Adapter.ActorAdapter;
import com.example.roomdatabaseretrofit.Network.Api;
import com.example.roomdatabaseretrofit.Repository.ActorRepository;
import com.example.roomdatabaseretrofit.ViewModel.ActorViewModel;
import com.example.roomdatabaseretrofit.models.Actor;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private ActorViewModel actorViewModel;
    private RecyclerView recyclerView;
    private static final String URL_DATA = "http://www.codingwithjks.tech/data.php/";
    private List<Actor> actorList;
    private ActorAdapter actorAdapter;
    private ActorRepository actorRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actorRepository = new ActorRepository(getApplication());
        actorList = new ArrayList<>();
        actorAdapter = new ActorAdapter(this,actorList);
        actorViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ActorViewModel.class);
        actorViewModel.getGetAllActors().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actorList) {
                actorAdapter.getAllActors(actorList);
                 recyclerView.setAdapter(actorAdapter);
                 Log.d("main","onChanged"+actorList);
            }
        });
        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_DATA).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<Actor>> call = api.getAllActors();
        call.enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                actorRepository.insert(response.body());
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}