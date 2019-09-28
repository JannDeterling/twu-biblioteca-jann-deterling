package com.twu.biblioteca;

import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;
import com.twu.biblioteca.menu.MainMenu;

public class BibliotecaApp {

    public static void main(String[] args) {
        WelcomeMessagePrinter.printWelcomeMessage();

        BookService bookService = new BookService(new SampleBookRepository());

        MainMenu mainMenu = new MainMenu();
        mainMenu.registerMenuOption("List books", bookService::printBookList);
        mainMenu.displayMenu();
    }
}
