package com.twu.biblioteca.movies;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

public class SampleMovieRepositoryTest {

    private SampleMovieRepository movieRepository;

    @Before
    public void setUpRepo() {
        this.movieRepository = new SampleMovieRepository();
    }

    @Test
    public void shouldReturnListOfMovies() {
        List<Movie> movies = movieRepository.getAll();
        assertThat(movies, is(not(nullValue(List.class))));
        assertThat(movies.size(), is(not(0)));
        assertThat(movies.size(), is(equalTo(3)));
    }

    @Test
    public void shouldReturnListOfAvailableMovies() {
        List<Movie> movies = movieRepository.getAll();
        movies.get(0).checkOut();
        List<Movie> moviesAvailable = movieRepository.getAllAvailable();
        assertThat(moviesAvailable, is(not(nullValue(List.class))));
        assertThat(moviesAvailable.size(), is(not(0)));
        assertThat(moviesAvailable.size(), is(equalTo(2)));
    }

    @Test
    public void shouldReturnOneMovie() {
        Optional<Movie> movieOptional = movieRepository.getOneByTitle("Lord of the Rings");
        assertThat(movieOptional.isPresent(), is(true));
    }

    @Test
    public void shouldReturnNoMovie() {
        Optional<Movie> movieOptional = movieRepository.getOneByTitle("Lord of the rings 2");
        assertThat(movieOptional.isPresent(), is(false));
    }
}
