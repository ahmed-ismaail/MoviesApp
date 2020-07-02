package com.example.ytsmoviesapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ytsmoviesapp.OnMovieClickListener;
import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    List<MovieModel> movieModelList = new ArrayList<>();
    Context context;
    OnMovieClickListener onMovieClickListener;

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false),onMovieClickListener);
    }

    public MoviesAdapter(Context context,OnMovieClickListener onMovieClickListener) {
        this.context = context;
        this.onMovieClickListener = onMovieClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {
        MovieModel movieModel = movieModelList.get(position);

        holder.movieNameTextView.setText(movieModel.getMovieName());
        holder.movieYearTextView.setText(movieModel.getMovieYear());

        Glide.with(context)
                .asBitmap()
                .load(movieModel.getMovieImageUrl())
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public void setMovieModelList(List<MovieModel> movieModelList) {
        this.movieModelList = movieModelList;
        notifyDataSetChanged();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movieNameTextView, movieYearTextView;
        ImageView movieImage;
        OnMovieClickListener onMovieClickListener;

        public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
            super(itemView);
            movieNameTextView = itemView.findViewById(R.id.movieName_textView);
            movieYearTextView = itemView.findViewById(R.id.movieYear_textView);
            movieImage = itemView.findViewById(R.id.movieImage);
            this.onMovieClickListener = onMovieClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieClickListener.onItemClickListener(getAdapterPosition());
        }
    }
}
