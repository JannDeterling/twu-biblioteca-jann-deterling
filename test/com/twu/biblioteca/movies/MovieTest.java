package com.twu.biblioteca.movies;

import org.junit.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.Year;

public class MovieTest {

    private static final String TITLE = "Lord of the Rings";
    private static final String DIRECTOR = "Peter Jackson";
    private static final Year PUBLISHED_YEAR = Year.of(2000);
    private static final MovieRating MOVIE_RATING = new MovieRating(10);

    @Test
    public void shouldCreateAValidMovie(){
        final Movie  movie = new Movie(TITLE, DIRECTOR, PUBLISHED_YEAR, MOVIE_RATING);

        assertThat(movie, is(not(nullValue(Movie.class))));
        assertThat(movie.getTitle(), is(equalTo(TITLE)));
        assertThat(movie.getDirector(), is(equalTo(DIRECTOR)));
        assertThat(movie.getPublishedYear(), is(equalTo(PUBLISHED_YEAR)));
        assertThat(movie.getMovieRating(), is(equalTo(MOVIE_RATING)));
    }

    @Test
    public void shouldCreateAValidMovieWithoutRating(){
        final Movie movie = new Movie(TITLE, DIRECTOR, PUBLISHED_YEAR);

        assertThat(movie, is(not(nullValue(Movie.class))));
        assertThat(movie.getTitle(), is(equalTo(TITLE)));
        assertThat(movie.getDirector(), is(equalTo(DIRECTOR)));
        assertThat(movie.getPublishedYear(), is(equalTo(PUBLISHED_YEAR)));
        assertThat(movie.getMovieRating().toString(), is(equalTo("unrated")));
    }
}
