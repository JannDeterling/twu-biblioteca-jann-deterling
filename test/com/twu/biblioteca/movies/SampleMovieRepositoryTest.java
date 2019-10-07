package com.twu.biblioteca.movies;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

public class SampleMovieRepositoryTest {

    @Test
    public void shouldReturnListOfMovies() {
        SampleMovieRepository movieRepository = new SampleMovieRepository();

        List<Movie> movies = movieRepository.getAll();
        assertThat(movies, is(not(nullValue(List.class))));
        assertThat(movies.size(), is(not(0)));
        assertThat(movies.size(), is(equalTo(3)));
    }

    @Test
    public void shouldReturnListOfAvailableMovies() {
        SampleMovieRepository movieRepository = new SampleMovieRepository();
        List<Movie> movies = movieRepository.getAll();
        movies.get(0).checkOut();
        List<Movie> moviesAvailable = movieRepository.getAllAvailable();
        assertThat(moviesAvailable, is(not(nullValue(List.class))));
        assertThat(moviesAvailable.size(), is(not(0)));
        assertThat(moviesAvailable.size(), is(equalTo(2)));
    }

}
