package com.twu.biblioteca.movies;

import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.core.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

    public void checkOutMovie() {
        this.printAvailableMovies();
        Optional<Movie> movieOptional = this.findMovieForUsageAction("Please enter the title of the movie you want to check out:");
        if(movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            if(movie.checkOut()){
                System.out.println("Thank you! Enjoy the movie.");
            }else {
                System.out.println("Sorry, that book is not available.");
            }
        }
    }

    private Optional<Movie> findMovieForUsageAction(String messageToDisplay){
        final Scanner scanner = new Scanner(System.in);
        System.out.println(messageToDisplay);
        String title = scanner.nextLine();
        return this.movieRepository.getOneByTitle(title);
    }

    private void printMovies(List<Movie> movies){
        movies.forEach(movie -> System.out.println(movie.toString()));
    }
}
