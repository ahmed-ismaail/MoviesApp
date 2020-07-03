package com.example.ytsmoviesapp.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.databinding.MovieDetailsBinding;
import com.example.ytsmoviesapp.model.MovieDetailsModel;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView movieRatingImage;
    MovieViewModel movieViewModel;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Movie Details");

        //databinding
        final MovieDetailsBinding movieDetailsBinding = DataBindingUtil.setContentView(this,R.layout.movie_details);
        movieDetailsBinding.setLifecycleOwner(this);

        progressBar = movieDetailsBinding.progressBar;
        progressBar.setVisibility(View.VISIBLE);

        //connect viewModel with main activity
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieRatingImage = findViewById(R.id.movieRating_imageView);

        int id = 0;
        int movieID = getIntent().getIntExtra("movieID", id);
        movieViewModel.getMovieDelails(movieID);

        movieViewModel.movieDetailsMutableLiveData.observe(this, new Observer<MovieDetailsModel>() {
            @Override
            public void onChanged(MovieDetailsModel movieDetailsModel) {
                progressBar.setVisibility(View.GONE);
                movieDetailsBinding.setMovieDetails(movieDetailsModel);
            }
        });
    }

    @BindingAdapter({"bind:imgUrl"})
    public static void setMovieIamge(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }
}