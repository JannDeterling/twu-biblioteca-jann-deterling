package com.twu.biblioteca.movies;

public class MovieRating {

    private Integer rating;

    public MovieRating(){
        this.setRating(0);
    }

    public MovieRating(Integer initialRating){
        this.setRating(initialRating);
    }

    public void setRating(Integer rating) {
        assert rating >=0 && rating <=10 : "The provided value must be in the range from 0-10";
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.rating != 0 ? String.valueOf(this.rating) : "unrated";
    }
}
