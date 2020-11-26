package com.example.roomdatabaseretrofit.Network;

import com.example.roomdatabaseretrofit.models.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {
    @GET("data.php")
    Call<List<Actor>> getAllActors();
}
