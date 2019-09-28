package com.twu.biblioteca.books;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleBookRepository implements BookRepository {

  private static final List<Book> SAMPLE_BOOK_LIST = new ArrayList<Book>(Arrays.asList(
      new Book("Refactoring: Improving the Design of Existing Code (2nd Edition)", "Martin Fowler", Year.of(2018)),
      new Book("Head First Design Patterns","Kathy Sierra, Elisabeth Robson", Year.of(2004)),
      new Book("Domain Driven Design", "Eric Evans", Year.of(2003))
  ));


  @Override
  public List<Book> getAllBooks() {
    return SAMPLE_BOOK_LIST;
  }
}
