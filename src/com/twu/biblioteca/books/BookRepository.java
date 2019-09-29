package com.twu.biblioteca.books;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

  List<Book> getAllBooks();

  Optional<Book> getBookByTitle(String title);
}
