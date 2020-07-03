package com.example.ytsmoviesapp.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ytsmoviesapp.model.MovieDetailsModel;
import com.example.ytsmoviesapp.model.MovieModel;
import com.example.ytsmoviesapp.model.data.MoviesRespository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    public MutableLiveData<List<MovieModel>> listMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<MovieDetailsModel> movieDetailsMutableLiveData = new MutableLiveData<>();
    private MoviesRespository moviesRespository = MoviesRespository.getInstance();

    public MutableLiveData<List<MovieModel>> fetchMovies() {

        listMutableLiveData = moviesRespository.fetchMovies();
        return listMutableLiveData;
    }

    public MutableLiveData<MovieDetailsModel> getMovieDelails(int movieID) {

        movieDetailsMutableLiveData = moviesRespository.getMovieDelails(movieID);
        return movieDetailsMutableLiveData;
    }
}
