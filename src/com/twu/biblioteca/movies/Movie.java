package com.twu.biblioteca.movies;

import java.time.Year;

public class Movie {
     private String title;
     private String director;
     private Year publishedYear;
     private MovieRating movieRating;

     public Movie(String title, String director, Year publishedYear, MovieRating movieRating){
         this.title = title;
         this.director = director;
         this.publishedYear = publishedYear;
         this.movieRating = movieRating;
     }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Year getPublishedYear() {
        return publishedYear;
    }

    public MovieRating getMovieRating() {
        return movieRating;
    }
}
