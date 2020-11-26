package com.example.roomdatabaseretrofit.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomdatabaseretrofit.models.Actor;

import java.util.List;

@Dao
public interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Actor> actorList);

    @Query("select * from actor")
    LiveData<List<Actor>> getAllActors();

    @Query("delete from actor")
    void deleteAll();
}
