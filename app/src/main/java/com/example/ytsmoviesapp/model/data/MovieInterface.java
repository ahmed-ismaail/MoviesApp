package com.example.ytsmoviesapp.model.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
    @GET("list_movies.json")
    Call<String> getMovies(@Query("quality") String quality);

}
