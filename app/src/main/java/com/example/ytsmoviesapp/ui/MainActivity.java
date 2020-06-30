package com.example.ytsmoviesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ytsmoviesapp.R;
import com.example.ytsmoviesapp.model.MovieModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MovieViewModel movieViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect viewModel with main activity
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.fetchMovies();

        recyclerView = findViewById(R.id.recyclerView);
        final MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieViewModel.listMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                moviesAdapter.setMovieModelList(movieModels);
            }
        });
    }
}
