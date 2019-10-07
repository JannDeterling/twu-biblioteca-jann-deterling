package com.twu.biblioteca.movies;

import com.twu.biblioteca.core.Repository;

import java.util.List;

public class MovieService {

    private final Repository<Movie> movieRepository;

    public MovieService(Repository<Movie> movieRepository){
        this.movieRepository = movieRepository;
    }

    public void printAllMovies(){
        this.printMovies(this.movieRepository.getAll());
    }

    public void printAvailableMovies() {
        this.printMovies(this.movieRepository.getAllAvailable());
    }

    private void printMovies(List<Movie> movies){
        movies.forEach(movie -> System.out.println(movie.toString()));
    }
}
