package com.twu.biblioteca.movies;

import com.twu.biblioteca.core.LibraryItemRepository;
import com.twu.biblioteca.core.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SampleMovieRepository implements LibraryItemRepository<Movie> {

    private final List<Movie> SAMPLE_MOVIE_LIST = new ArrayList<Movie>(Arrays.asList(
            new Movie("Lord of the Rings", "Peter Jackson", Year.of(2000), new MovieRating(10)),
            new Movie("Stars Wars","Georg Lucas", Year.of(1990), new MovieRating(5)),
            new Movie("Toy Story 4", "Disney", Year.of(2019), new MovieRating(0))
    ));

    @Override
    public List<Movie> getAll() {
        return this.SAMPLE_MOVIE_LIST;
    }

    @Override
    public List<Movie> getAllAvailable() {
        return this.SAMPLE_MOVIE_LIST.stream().filter(movie -> !movie.isCheckedOut()).collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> getOneByTitle(String title) {
        return this.SAMPLE_MOVIE_LIST.stream().filter(movie -> movie.getTitle().equals(title)).findFirst();
    }
}
