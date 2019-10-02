package com.example.yaratest.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.yaratest.dao.MovieDao;
import com.example.yaratest.model.entity.MovieEntity;
import com.example.yaratest.tools.ApplicationDatabase;

import java.util.List;

public class MovieDatabaseRepository {
    private MovieDao movieDao;
    private LiveData<List<MovieEntity>> movies;

    public MovieDatabaseRepository(Application application) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);
        movieDao = database.movieDao();
        movies = movieDao.getAllMovie();
    }

    public void insert(MovieEntity movieEntity){
        new InsertMovieAsyncTask(movieDao).execute(movieEntity);
    }

    public LiveData<List<MovieEntity>> getAllMovie(){
        return movies;
    }



    public static class InsertMovieAsyncTask extends AsyncTask<MovieEntity, Void, Void>{

        private MovieDao movieDao;
        private InsertMovieAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(MovieEntity... movieEntities) {
            movieDao.insert(movieEntities[0]);
            return null;
        }
    }
}
