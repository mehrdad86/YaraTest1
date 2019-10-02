package com.example.yaratest.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.yaratest.R;
import com.example.yaratest.databinding.ActivityMovieDetailBinding;
import com.example.yaratest.model.MovieDetail;
import com.example.yaratest.viewmodel.MovieApiViewModel;
import com.example.yaratest.viewmodel.activity.DetailMovieVM;

public class MovieDetailActicity extends AppCompatActivity {

    MovieApiViewModel movieApiViewModel;
    String movieId = null;
    ActivityMovieDetailBinding binding;
    DetailMovieVM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        viewModel = new DetailMovieVM();
        binding.setViewmodel(viewModel);
        movieApiViewModel = ViewModelProviders.of(this).get(MovieApiViewModel.class);

        movieId = getIntent().getStringExtra("id");
        if (movieId != null){
            getMovieDetail(movieId);
        }
    }


    private void getMovieDetail(String id){
        movieApiViewModel.getMovieDetail(id).observe(this, new Observer<MovieDetail>() {
            @Override
            public void onChanged(MovieDetail movieDetail) {
                if (movieDetail != null){
                    injectData(movieDetail);
                    Log.i("test123","movieDetail is not null");
                }else {
                    Log.i("test123","movieDetail is null");
                }
            }
        });
    }

    private void injectData(MovieDetail movieDetail){

        Glide.with(getApplicationContext())
                .load(movieDetail.getPoster())
                .into(binding.imgPoster);

        viewModel.title.set(movieDetail.getTitle());
        viewModel.genre.set(movieDetail.getGenre());
        viewModel.outDate.set(movieDetail.getReleased());
        viewModel.runTime.set(movieDetail.getRuntime());
        viewModel.plot.set(movieDetail.getPlot());
        viewModel.director.set(movieDetail.getDirector());
        viewModel.actors.set(movieDetail.getActors());
        viewModel.awards.set(movieDetail.getAwards());
        viewModel.language.set(movieDetail.getLanguage());
        viewModel.boxOffice.set(movieDetail.getBoxOffice());
        viewModel.production.set(movieDetail.getProduction());
        viewModel.imdbRating.set(movieDetail.getImdbRating());
        viewModel.imdbVotes.set(movieDetail.getImdbVotes());
        viewModel.webSite.set(movieDetail.getWebsite());

    }

}
