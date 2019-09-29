package com.twu.biblioteca;

import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;
import com.twu.biblioteca.menu.MainMenu;

public class BibliotecaApp {

    private static boolean shouldQuit = false;

    public static void main(String[] args) {
        WelcomeMessagePrinter.printWelcomeMessage();

        BookService bookService = new BookService(new SampleBookRepository());

        MainMenu mainMenu = new MainMenu();
        mainMenu.registerMenuOption("List books", bookService::printBookList);
        mainMenu.registerMenuOption("Quit Biblioteca", BibliotecaApp::quitBibliotecaApp);
        while (!shouldQuit) {
          mainMenu.displayMenu();
        }
    }

    public static void quitBibliotecaApp() {
       shouldQuit = true;
       System.out.println("Thanks for using Biblioteca!\nHave a great day!");
    }
}
