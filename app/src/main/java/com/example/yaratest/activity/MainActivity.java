package com.example.yaratest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yaratest.R;
import com.example.yaratest.adapter.RecyclerMovieAdapter;
import com.example.yaratest.model.Movie;
import com.example.yaratest.model.MovieItem;
import com.example.yaratest.model.entity.MovieEntity;
import com.example.yaratest.viewmodel.MovieApiViewModel;
import com.example.yaratest.viewmodel.MovieDatabaseViewModel;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MovieApiViewModel movieApiViewModel;
    MovieDatabaseViewModel movieDatabaseViewModel;
    List<MovieItem> movies = new ArrayList<>();
    RecyclerView recyclerMovie;
    RecyclerMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieApiViewModel = ViewModelProviders.of(this).get(MovieApiViewModel.class);
        movieDatabaseViewModel = ViewModelProviders.of(this).get(MovieDatabaseViewModel.class);

        initRecyclerView();
        getMovies();
    }

    private void initRecyclerView(){
        recyclerMovie = findViewById(R.id.recyclerMovie);
        recyclerMovie.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new RecyclerMovieAdapter();
        recyclerMovie.setAdapter(adapter);

        adapter.setListener(new RecyclerMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieItem movieItem) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActicity.class);
                intent.putExtra("id",movieItem.getImdbID());
                startActivity(intent);
            }
        });
    }

    private void getMovies(){
        movieApiViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie == null){
                    getMovieFromDB();
                    Log.i("test123","movie is null get movie from db");
                }else {
                    Log.i("test123","movie is not null");
                    movies = movie.getSearch();
                    adapter.setMovies(movies);
                    if (Hawk.get("movie") == null){
                        insertIntoSql();
                    }
                }
            }
        });
    }

    private void insertIntoSql(){
        if (movies.size() > 0){
            for (MovieItem item : movies){
                MovieEntity entity = new MovieEntity();
                entity.setTitle(item.getTitle());
                entity.setImdbID(item.getImdbID());
                entity.setPoster(item.getPoster());
                entity.setType(item.getType());
                entity.setYear(item.getYear());

                movieDatabaseViewModel.insert(entity);
            }

            Hawk.put("movie", "full");
        }
    }

    private void getMovieFromDB(){
        Log.i("test123","in the getMovieFromDB");
        movieDatabaseViewModel.getAllMovie().observe(this, new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(List<MovieEntity> movieEntities) {
                if (movieEntities == null || movieEntities.size() == 0 ){
                    Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                }else {
                    for (MovieEntity entity : movieEntities){
                        MovieItem item = new MovieItem();
                        item.setImdbID(entity.getImdbID());
                        item.setPoster(entity.getPoster());
                        item.setTitle(entity.getTitle());
                        item.setType(entity.getType());
                        item.setYear(entity.getYear());
                        movies.add(item);
                    }
                    adapter.setMovies(movies);
                }
            }
        });
    }
}
