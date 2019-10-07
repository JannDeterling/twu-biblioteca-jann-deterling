package com.twu.biblioteca.movies;

import com.twu.biblioteca.core.Repository;

public class MovieService {

    private final Repository<Movie> movieRepository;

    public MovieService(Repository<Movie> movieRepository){
        this.movieRepository = movieRepository;
    }

    public void printMovieList(){
        this.movieRepository.getAll().forEach(movie -> System.out.println(movie.toString()));
    }
}
