package com.twu.biblioteca;

import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;

public class BibliotecaApp {

    public static void main(String[] args) {
        WelcomeMessagePrinter.printWelcomeMessage();

        BookService bookService = new BookService(new SampleBookRepository());
        bookService.printBookList();
    }
}
