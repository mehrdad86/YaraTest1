package com.example.yaratest.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yaratest.model.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    void insert(MovieEntity movieEntity);

    @Update
    void update(MovieEntity movieEntity);

    @Delete
    void delete(MovieEntity movieEntity);

    @Query("SELECT * FROM movie_table")
    LiveData<List<MovieEntity>> getAllMovie();
}
