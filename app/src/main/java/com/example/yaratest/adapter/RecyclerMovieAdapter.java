package com.example.yaratest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yaratest.R;
import com.example.yaratest.model.Movie;
import com.example.yaratest.model.MovieItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerMovieAdapter.MyViewHolder> {

    List<MovieItem> movies = new ArrayList<>();
    OnItemClickListener listener;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MovieItem movie = movies.get(position);

        holder.txtName.setText(movie.getTitle());
        holder.txtYear.setText(movie.getYear());

        Glide.with(holder.imgPoster.getContext())
                .load(movie.getPoster())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPoster;
        TextView txtName;
        TextView txtYear;
        LinearLayout linearHolder;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            txtName = itemView.findViewById(R.id.txtName);
            txtYear = itemView.findViewById(R.id.txtYear);
            linearHolder = itemView.findViewById(R.id.linearHolder);

            linearHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(movies.get(position));
                    }
                }
            });
        }
    }


    public interface OnItemClickListener{
        void onItemClick(MovieItem movieItem);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
