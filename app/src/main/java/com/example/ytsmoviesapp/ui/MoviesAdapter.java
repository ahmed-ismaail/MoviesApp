package com.example.ytsmoviesapp.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.model.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    List<MovieModel> movieModelList = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    public MoviesAdapter(Context context) {
        this.context = context;
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

    public void setMovieModelList(List<MovieModel> movieModelList){
        this.movieModelList = movieModelList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView movieNameTextView,movieYearTextView;
        ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTextView = itemView.findViewById(R.id.movieName_textView);
            movieYearTextView = itemView.findViewById(R.id.movieYear_textView);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}
