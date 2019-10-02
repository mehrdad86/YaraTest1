package com.example.yaratest.viewmodel;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.yaratest.model.entity.MovieEntity;
import com.example.yaratest.repository.MovieDatabaseRepository;

import java.util.List;

public class MovieDatabaseViewModel extends AndroidViewModel {

    MovieDatabaseRepository repository;
    LiveData<List<MovieEntity>> allMovie;

    public MovieDatabaseViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDatabaseRepository(application);
        allMovie = repository.getAllMovie();
    }

    public void insert(MovieEntity entity){
        repository.insert(entity);
    }

    public LiveData<List<MovieEntity>> getAllMovie(){
        return allMovie;
    }
}
