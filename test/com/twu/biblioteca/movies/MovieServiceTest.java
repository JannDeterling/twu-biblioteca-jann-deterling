package com.twu.biblioteca.movies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class MovieServiceTest {


    private SampleMovieRepository movieRepository;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOutput;

    @Before
    public void setUpOutput() {
        movieRepository = new SampleMovieRepository();
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void shouldPrintAllBooks(){
        MovieService movieService = new MovieService(movieRepository);
        movieService.printAllMovies();
        assertThat(testOutput.toString(), is(equalTo(expectedPrintedBooks(this.movieRepository.getAll()))));
    }

    @Test
    public void shouldOnlyPrintAvailableBooks(){
        Optional<Movie> movieOptional = movieRepository.getOneByTitle("Lord of the Rings");
        movieOptional.ifPresent(Movie::checkOut);

        MovieService movieService = new MovieService(movieRepository);
        movieService.printAvailableMovies();
        assertThat(testOutput.toString(), is(equalTo(expectedPrintedBooks(this.movieRepository.getAllAvailable()))));
    }

    private void provideTestInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String expectedPrintedBooks(List<Movie> movies) {
        final StringBuilder stringBuilder = new StringBuilder();
        movies.forEach(movie -> stringBuilder.append(movie.toString()).append(System.lineSeparator()));
        return stringBuilder.toString();
    }
}
