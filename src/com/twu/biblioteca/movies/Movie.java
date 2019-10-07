package com.twu.biblioteca.movies;

import java.time.Year;

public class Movie {
     private String title;
     private String director;
     private Year publishedYear;
     private MovieRating movieRating;

     public Movie(String title, String director, Year publishedYear){
         this(title, director, publishedYear, new MovieRating(0));
     }

     public Movie(String title, String director, Year publishedYear, MovieRating movieRating){
         this.setTitle(title);
         this.setDirector(director);
         this.setPublishedYear(publishedYear);
         this.setMovieRating(movieRating);
     }

    private void setTitle(String title) {
         assert title != null && !title.equals("") : "The movie title should not be empty or null";
         this.title = title;
    }

    private void setDirector(String director) {
        this.director = director;
    }

    private void setPublishedYear(Year publishedYear) {
        this.publishedYear = publishedYear;
    }

    private void setMovieRating(MovieRating movieRating) {
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
