package com.example.ytsmoviesapp.ui;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.model.MovieDetailsModel;

public class MovieDetailsActivity extends AppCompatActivity {

    TextView movieNameTextView, movieYearTextView, movieGenreTextView,
            movieRatingTextView, movieDescriptionTextView, movieTextDescriptionTextView;

    ImageView movieRatingImage, movieImage;

    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        setTitle("Movie Details");

        //connect viewModel with main activity
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieNameTextView = findViewById(R.id.movieDetailsName_textView);
        movieYearTextView = findViewById(R.id.movieDetailsYear_textView);
        movieGenreTextView = findViewById(R.id.movieGenre_TextView);
        movieRatingTextView = findViewById(R.id.movieRating_textView);
        movieDescriptionTextView = findViewById(R.id.Description_textView);
        movieTextDescriptionTextView = findViewById(R.id.textDescription_textView);

        movieRatingImage = findViewById(R.id.movieRating_imageView);
        movieImage = findViewById(R.id.movieDetailsImage);

        int id=0;
        int movieID = getIntent().getIntExtra("movieID",id);
        movieViewModel.getMovieDelails(movieID);


        movieViewModel.movieDetailsMutableLiveData.observe(this, new Observer<MovieDetailsModel>() {
            @Override
            public void onChanged(MovieDetailsModel movieDetailsModel) {
                movieNameTextView.setText(movieDetailsModel.getMovieName());
                movieYearTextView.setText(movieDetailsModel.getMovieYear());
                movieGenreTextView.setText(movieDetailsModel.getMovieGenre());
                movieRatingTextView.setText(String.valueOf(movieDetailsModel.getMovieRating()));
                movieTextDescriptionTextView.setText(movieDetailsModel.getMovieDescription());

                Glide.with(getBaseContext())
                        .asBitmap()
                        .load(movieDetailsModel.getMovieImageUrl())
                        .into(movieImage);
            }
        });
    }
}
