package com.example.ytsmoviesapp.model.data;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    private static final String BASE_URL = "https://yts.mx/api/v2/";
    private MovieInterface movieInterface;

    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return Singleton.INSTANCE;
    }

    private RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        movieInterface = retrofit.create(MovieInterface.class);
    }

    public Call<String> getMovies() {
        return movieInterface.getMovies("2160p");
    }

    public Call<String> getSelectedMovie(int id) {
        return movieInterface.getSelectedMovie(id);
    }
}
