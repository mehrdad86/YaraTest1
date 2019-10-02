package com.example.yaratest.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.yaratest.api.ApiService;
import com.example.yaratest.api.RetroClass;
import com.example.yaratest.model.Movie;
import com.example.yaratest.model.MovieDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiRepository {

    ApiService apiService;
    public MovieApiRepository(){
        apiService = RetroClass.getApiService();
    }

    public MutableLiveData<Movie> getMovies(){
        final MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();
        apiService.getMovie("Batman").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.code() == 200){
                    movieLiveData.setValue(response.body());
                }else{
                    movieLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                movieLiveData.setValue(null);
            }
        });

        return movieLiveData;
    }

    public MutableLiveData<MovieDetail> getMovieDetail(String id){
        final MutableLiveData<MovieDetail> movieDetailLiveData = new MutableLiveData<>();
        apiService.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.code() == 200){
                    movieDetailLiveData.setValue(response.body());
                }else {
                    movieDetailLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                movieDetailLiveData.setValue(null);
            }
        });

        return movieDetailLiveData;
    }

}
