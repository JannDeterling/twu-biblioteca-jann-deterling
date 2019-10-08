package com.twu.biblioteca;

import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;
import com.twu.biblioteca.menu.MainMenu;
import com.twu.biblioteca.movies.MovieService;
import com.twu.biblioteca.movies.SampleMovieRepository;
import com.twu.biblioteca.users.LoggedInUserSingleton;
import com.twu.biblioteca.users.SampleUserRepository;
import com.twu.biblioteca.users.User;
import com.twu.biblioteca.users.UserService;

import java.util.Optional;

public class BibliotecaApp {

    private static Boolean shouldQuit = false;

    public static void main(String[] args) {
        WelcomeMessagePrinter.printWelcomeMessage();

        UserService userService = new UserService(new SampleUserRepository());
        BookService bookService = new BookService(new SampleBookRepository(), userService);
        MovieService movieService = new MovieService(new SampleMovieRepository());

        MainMenu mainMenu = new MainMenu();
        mainMenu.registerMenuOption("List available books", bookService::printAvailableBookList);
        mainMenu.registerMenuOption("Check out a book", bookService::checkOutBook);
        mainMenu.registerMenuOption("Return a book", bookService::returnBook);

        mainMenu.registerMenuOption("List available movies", movieService::printAvailableMovies);
        mainMenu.registerMenuOption("Check out a movie", movieService::checkOutMovie);


        mainMenu.registerMenuOption("Quit Biblioteca", BibliotecaApp::quitBibliotecaApp);

        Boolean checkLoggedInUser = true;
        while (!shouldQuit) {
          mainMenu.displayMenu();
          if (checkLoggedInUser) {
              Optional<User> userOptional = LoggedInUserSingleton.getInstance().getLoggedInUser();
              if (userOptional.isPresent() && userOptional.get().isLoggedIn()){
                  mainMenu.registerMenuOption("Show personal informations", userService::printLoggedInUserInformation);
              }
          }
        }
    }

    public static void quitBibliotecaApp() {
       shouldQuit = true;
       System.out.println("Thanks for using Biblioteca!\nHave a great day!");
    }
}
