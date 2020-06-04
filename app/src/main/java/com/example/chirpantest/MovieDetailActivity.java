package com.example.chirpantest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
   ImageView coverImage,posterImage;
   TextView description,launhuage,releasedate,revenue,popularity;
  int genre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        coverImage=findViewById(R.id.image);
        posterImage=findViewById(R.id.poster_image);
        description=findViewById(R.id.disc);
        launhuage=findViewById(R.id.language);
        releasedate=findViewById(R.id.release_date);
        revenue=findViewById(R.id.revenue);
        popularity=findViewById(R.id.popularity);

        genre=getIntent().getIntExtra("Genre",0);
       // Toast.makeText(this,"genre id " + genre,Toast.LENGTH_SHORT).show();
          getdata(genre);
    }
    private void getdata(int genre) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.Baseurl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<MovieDetailsResponse> call = api.getMovieDeatils(genre,"68e82445c8842ba8616d0f6bf0e97a41");

        call.enqueue(new Callback<MovieDetailsResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, retrofit2.Response<MovieDetailsResponse> response) {
                MovieDetailsResponse responseHome = response.body();

                if(responseHome != null) {
                    List<Result> additionalProperties = responseHome.getResults();

                    popularity.setText("Popularity : " + additionalProperties.get(0).getPopularity());
                    launhuage.setText("Launguage : " + additionalProperties.get(0).getOriginalLanguage());
                    releasedate.setText("Release Date : " + additionalProperties.get(0).getReleaseDate());
                    revenue.setText("Revenue : " + additionalProperties.get(0).getTitle());
                    description.setText(additionalProperties.get(0).getOverview());


                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.ic_local_movies_black_24dp);
                    Glide.with(MovieDetailActivity.this).setDefaultRequestOptions(requestOptions).
                            load("https://api.themoviedb.org"+additionalProperties.get(0).getBackdropPath()).into(coverImage);


                    Glide.with(MovieDetailActivity.this).setDefaultRequestOptions(requestOptions).
                            load("https://api.themoviedb.org"+additionalProperties.get(0).getPosterPath()).into(posterImage);

                }
            }

            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
