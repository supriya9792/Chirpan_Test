package com.example.chirpantest;

public class MovieList {
    String movieName;
    String posterImage;

    public MovieList(String movieName, String posterImage) {
        this.movieName = movieName;
        this.posterImage = posterImage;
    }

    public MovieList() {
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
