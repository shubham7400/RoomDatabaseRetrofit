package com.example.roomdatabaseretrofit.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabaseretrofit.Repository.ActorRepository;
import com.example.roomdatabaseretrofit.models.Actor;

import java.util.List;

public class ActorViewModel extends AndroidViewModel {

    private ActorRepository actorRepository;
    private LiveData<List<Actor>> getAllActors;

    public ActorViewModel(@NonNull Application application) {
        super(application);
        actorRepository = new ActorRepository(application);
        getAllActors = actorRepository.getGetAllActors();
    }

    public void insert(List<Actor> list)
    {
        actorRepository.insert(list);
    }

    public LiveData<List<Actor>> getGetAllActors()
    {
        return getAllActors;
    }
}
