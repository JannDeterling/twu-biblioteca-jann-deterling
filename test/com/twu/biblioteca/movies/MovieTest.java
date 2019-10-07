package com.twu.biblioteca.movies;

import org.junit.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.Year;

public class MovieTest {

    @Test
    public void shouldCreateAValidMovie(){
        String title = "Lord of the Rings";
        String director = "Peter Jackson";
        Year publishedYear = Year.of(2000);
        MovieRating movieRating = new MovieRating(10);

        Movie  movie = new Movie(title, director, publishedYear, movieRating);

        assertThat(movie, is(not(nullValue(Movie.class))));
        assertThat(movie.getTitle(), is(equalTo(title)));
        assertThat(movie.getDirector(), is(equalTo(director)));
        assertThat(movie.getPublishedYear(), is(equalTo(publishedYear)));
        assertThat(movie.getMovieRating, is(equalTo(movieRating)));
    }

}
