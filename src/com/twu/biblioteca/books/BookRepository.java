package com.twu.biblioteca.books;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

  List<Book> getAllBooks();

  List<Book> getAllBooksAvailable();

  Optional<Book> getBookByTitle(String title);
}
