package com.twu.biblioteca.books;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleBookRepository implements BookRepository {

  private static final List<Book> SAMPLE_BOOK_LIST = new ArrayList<Book>(Arrays.asList(
      new Book("Refactoring: Improving the Design of Existing Code (2nd Edition)"),
      new Book("Head First Design Patterns"),
      new Book("Domain Driven Design")
  ));


  @Override
  public List<Book> getAllBooks() {
    return SAMPLE_BOOK_LIST;
  }
}
