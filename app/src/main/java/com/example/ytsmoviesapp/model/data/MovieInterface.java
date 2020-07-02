package com.example.ytsmoviesapp.model.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
    @GET("list_movies.json")
    Call<String> getMovies(@Query("quality") String quality);

    @GET("movie_details.json")
    Call<String> getSelectedMovie(@Query("movie_id") int movieID);

}
