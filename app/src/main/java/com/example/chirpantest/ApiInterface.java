package com.example.chirpantest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    String Baseurl="https://api.themoviedb.org/3/genre/";
    @GET("movie/list")
    Call<MovieResponse> getMovies(@Query("api_key") String api_key);


    @GET("{genre}/movies")
    Call<MovieDetailsResponse> getMovieDeatils(@Path("genre") int genre,@Query("api_key") String api_key);
}
