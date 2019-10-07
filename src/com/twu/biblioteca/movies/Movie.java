package com.twu.biblioteca.movies;

import java.time.Year;

public class Movie {
     private String title;
     private String director;
     private Year publishedYear;
     private MovieRating movieRating;
     private Boolean isCheckedOut;

     public Movie(String title, String director, Year publishedYear){
         this(title, director, publishedYear, new MovieRating(0));
     }

     public Movie(String title, String director, Year publishedYear, MovieRating movieRating){
         this.setTitle(title);
         this.setDirector(director);
         this.setPublishedYear(publishedYear);
         this.setMovieRating(movieRating);
         this.isCheckedOut = false;
     }

    private void setTitle(String title) {
         assert title != null && !title.equals("") : "The movie title should not be empty or null";
         this.title = title;
    }

    private void setDirector(String director) {
         assert director != null && !director.equals("") : "The movie director should not be empty or null";
         this.director = director;
    }

    private void setPublishedYear(Year publishedYear) {
         assert publishedYear != null : "The publishedYear should not be null";
         this.publishedYear = publishedYear;
    }

    private void setMovieRating(MovieRating movieRating) {
         this.movieRating = movieRating;
    }

    public Boolean checkOut() {
         if (this.isCheckedOut){
             return false;
         }else{
             this.isCheckedOut = true;
             return true;
         }
    }

    public Boolean isCheckedOut() {
        return isCheckedOut;
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", publishedYear=" + publishedYear +
                ", movieRating=" + movieRating +
                '}';
    }
}
