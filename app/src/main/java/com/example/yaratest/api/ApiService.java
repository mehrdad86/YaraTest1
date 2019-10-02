package com.example.yaratest.api;

import com.example.yaratest.model.Movie;
import com.example.yaratest.model.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("?apikey=3e974fca")
    Call<Movie> getMovie(@Query("s") String s);

    @GET("?apikey=3e974fca")
    Call<MovieDetail> getMovieDetail(@Query("i") String i);
}
