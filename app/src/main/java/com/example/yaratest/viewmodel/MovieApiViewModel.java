package com.example.yaratest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yaratest.model.Movie;
import com.example.yaratest.model.MovieDetail;
import com.example.yaratest.repository.MovieApiRepository;

public class MovieApiViewModel extends ViewModel {

    private MovieApiRepository repository;
    private MutableLiveData<Movie> movieLiveData;
    private MutableLiveData<MovieDetail> movieDetailLiveData;

    public MovieApiViewModel(){
        repository = new MovieApiRepository();
    }

    public LiveData<Movie> getMovie(){
        movieLiveData = repository.getMovies();
        return movieLiveData;
    }

    public LiveData<MovieDetail> getMovieDetail(String id){
        movieDetailLiveData = repository.getMovieDetail(id);
        return movieDetailLiveData;
    }
}
