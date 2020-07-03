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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    List<MovieModel> movieModelList = new ArrayList<>();
    MovieViewModel movieViewModel;
    MoviesAdapter moviesAdapter;
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

        movieViewModel.listMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                progressBar.setVisibility(View.GONE);
                movieModelList.addAll(movieModels);
                moviesAdapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(MainActivity.this, movieModelList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(moviesAdapter);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
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
