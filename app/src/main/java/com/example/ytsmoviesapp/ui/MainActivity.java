package com.example.ytsmoviesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.ytsmoviesapp.OnMovieClickListener;
import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.databinding.ActivityMainBinding;
import com.example.ytsmoviesapp.model.MovieModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    MovieViewModel movieViewModel;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //databinding
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);

        //connect viewModel with main activity
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.fetchMovies();

        progressBar = activityMainBinding.progressCircular;
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = activityMainBinding.recyclerview;
        final MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieViewModel.listMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                progressBar.setVisibility(View.GONE);
                moviesAdapter.setMovieModelList(movieModels);
            }
        });
    }

    @Override
    public void onItemClickListener(MovieModel movieModel) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movieID", movieModel.getID());
        startActivity(intent);
    }

    @BindingAdapter("bind:imageUrl")
    public static void setMovieImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }
}
