package com.example.ytsmoviesapp.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytsmoviesapp.OnMovieClickListener;
import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.databinding.MovieItemBinding;
import com.example.ytsmoviesapp.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieModel> movieModelList;
    private OnMovieClickListener onMovieClickListener;

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent, false);
        return new MovieViewHolder(movieItemBinding);
    }

    public MoviesAdapter(OnMovieClickListener onMovieClickListener,List<MovieModel> movieModelList) {
        this.onMovieClickListener = onMovieClickListener;
        this.movieModelList = movieModelList;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {
        MovieModel movieModel = movieModelList.get(position);
        holder.bind(movieModel, onMovieClickListener);
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        // Since our layout file is movie_item.xml, our auto generated binding class is MovieItemBinding
        private MovieItemBinding itemBinding;

        //constructor taking a MovieItemBinding as its parameter
        public MovieViewHolder(MovieItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(final MovieModel movieModel, final OnMovieClickListener onMovieClickListener) {
            itemBinding.setMovie(movieModel);
            itemBinding.executePendingBindings();

            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieClickListener.onItemClickListener(movieModel);
                }
            });
        }
    }
}
