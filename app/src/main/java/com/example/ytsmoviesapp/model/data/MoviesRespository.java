package com.example.ytsmoviesapp.model.data;

import androidx.lifecycle.MutableLiveData;

import com.example.ytsmoviesapp.model.MovieDetailsModel;
import com.example.ytsmoviesapp.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRespository {
    private static MoviesRespository moviesRespository;

    public static MoviesRespository getInstance() {
        if (moviesRespository == null) {
            moviesRespository = new MoviesRespository();
        }
        return moviesRespository;
    }

    public MutableLiveData<List<MovieModel>> fetchMovies() {
        final MutableLiveData<List<MovieModel>> listMutableLiveData = new MutableLiveData<>();
        final List<MovieModel> movieModelList = new ArrayList<>();

        RetrofitHelper.getInstance().getMovies().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()));
                    String statusCode = jsonObject.getString("status");

                    if (statusCode.equals("ok")) {
                        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("movies");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            MovieModel movieModel = new MovieModel();

                            movieModel.setID(jsonArray.getJSONObject(i).getInt("id"));
                            movieModel.setMovieName(jsonArray.getJSONObject(i).getString("title"));
                            movieModel.setMovieYear(jsonArray.getJSONObject(i).getString("year"));
                            movieModel.setMovieImageUrl(jsonArray.getJSONObject(i).getString("large_cover_image"));

                            movieModelList.add(movieModel);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listMutableLiveData.setValue(movieModelList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listMutableLiveData.setValue(null);
            }
        });
        return listMutableLiveData;
    }

    public MutableLiveData<MovieDetailsModel> getMovieDelails(int movieID) {
        final MutableLiveData<MovieDetailsModel> movieDetailsMutableLiveData = new MutableLiveData<>();
        final MovieDetailsModel movieDetailsModel = new MovieDetailsModel();

        RetrofitHelper.getInstance().getSelectedMovie(movieID).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()));
                    String statusCode = jsonObject.getString("status");

                    if (statusCode.equals("ok")) {
                        JSONObject movieObject = jsonObject.getJSONObject("data").getJSONObject("movie");

                        movieDetailsModel.setMovieName(movieObject.getString("title"));
                        movieDetailsModel.setMovieYear(movieObject.getString("year"));
                        movieDetailsModel.setMovieImageUrl(movieObject.getString("large_cover_image"));
                        JSONArray genreArray = movieObject.getJSONArray("genres");
                        String movieGenre = "";
                        for (int i = 0; i < genreArray.length(); i++) {
                            movieGenre += genreArray.getString(i);
                            if (i < genreArray.length() - 1) {
                                movieGenre += " / ";
                            }
                            movieDetailsModel.setMovieGenre(movieGenre);
                        }
                        movieDetailsModel.setMovieRating(movieObject.getInt("rating"));
                        movieDetailsModel.setMovieDescription(movieObject.getString("description_full"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                movieDetailsMutableLiveData.setValue(movieDetailsModel);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                movieDetailsMutableLiveData.setValue(null);
            }
        });
        return movieDetailsMutableLiveData;
    }
}
