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
    }

}
