package com.twu.biblioteca.books;

import com.twu.biblioteca.core.LibraryItemRepository;
import com.twu.biblioteca.core.Repository;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SampleBookRepository implements LibraryItemRepository<Book> {

  private final List<Book> SAMPLE_BOOK_LIST = new ArrayList<Book>(Arrays.asList(
      new Book("Refactoring: Improving the Design of Existing Code (2nd Edition)", "Martin Fowler", Year.of(2018)),
      new Book("Head First Design Patterns","Kathy Sierra, Elisabeth Robson", Year.of(2004)),
      new Book("Domain Driven Design", "Eric Evans", Year.of(2003))
  ));


  @Override
  public List<Book> getAll() {
    return SAMPLE_BOOK_LIST;
}

  @Override
  public List<Book> getAllAvailable() {
    return SAMPLE_BOOK_LIST.stream().filter(book -> !book.isCheckedOut()).collect(Collectors.toList());
  }

  @Override
  public Optional<Book> getOneByTitle(String title){
    return SAMPLE_BOOK_LIST.stream().filter(book -> book.getTitle().equals(title)).findAny();
  }
}
