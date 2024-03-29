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
    public void shouldCheckOutAMovie(){
        final Movie movie = new Movie(TITLE, DIRECTOR, PUBLISHED_YEAR, MOVIE_RATING);
        assertThat(movie.checkOut(), is(true));
        assertThat(movie.isCheckedOut(), is(true));
        assertThat(movie.checkOut(), is(false));
        assertThat(movie.isCheckedOut(), is(true));
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

    @Test(expected = AssertionError.class)
    public void shouldNotCreateAValidMovieWithoutTitle(){
        new Movie("", DIRECTOR, PUBLISHED_YEAR, MOVIE_RATING);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateAValidMovieWithNullTitle(){
        new Movie(null, DIRECTOR, PUBLISHED_YEAR, MOVIE_RATING);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateAValidMovieWithoutDirector(){
        new Movie( TITLE, "", PUBLISHED_YEAR);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateAValidMovieWithNullDirector(){
        new Movie( TITLE, null, PUBLISHED_YEAR);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateAValidMovieWithNullPublishedYear(){
        new Movie(TITLE, DIRECTOR, null);
    }
}
