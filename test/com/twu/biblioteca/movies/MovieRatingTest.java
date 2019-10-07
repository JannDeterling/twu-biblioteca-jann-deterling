package com.twu.biblioteca.movies;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MovieRatingTest {

    @Test
    public void shouldCreateMovieRating(){
        final MovieRating movieRating = new MovieRating();

        assertThat(movieRating, is(not(nullValue(MovieRating.class))));
        assertThat(movieRating.toString(), is(equalTo("unrated")));

        movieRating.setRating(10);
        assertThat(movieRating.toString(), is(equalTo("10")));
    }

    @Test
    public void shouldCreateMovieRatingWithInitialValue(){
        final MovieRating movieRating = new MovieRating(0);
        assertThat(movieRating, is(not(nullValue(MovieRating.class))));
        assertThat(movieRating.toString(), is(equalTo("unrated")));

    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateMovieRatingWithTooLowInitialValue(){
        new MovieRating(-1);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateMovieRatingWithTooHighInitialValue(){
        new MovieRating(11);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotChangeMovieRatingToHigherValue(){
        final MovieRating movieRating = new MovieRating();
        movieRating.setRating(20);
    }

    @Test(expected = AssertionError.class)
    public void  shouldNotChangeMovieRatingToLowerValue(){
        final MovieRating movieRating = new MovieRating();
        movieRating.setRating(-10);
    }

    @Test(expected = AssertionError.class)
    public void shouldNotCreateMovieRating(){
        new MovieRating(-1);
    }
}
