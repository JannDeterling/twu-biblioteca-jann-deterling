package com.twu.biblioteca.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class SampleBookRepositoryTest {

  @Test
  public void shouldReturnListOfAllBooks() {
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    List<Book> bookList = sampleBookRepository.getAllBooks();

    assertNotNull(bookList);
    assertNotEquals(bookList.size(), 0);
    assertEquals(bookList.size(), 3);
  }

  @Test
  public void shouldGetBookByTitle(){
    String title = "Domain Driven Design";
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    Optional<Book> optionalBook = sampleBookRepository.getBookByTitle(title);
    assertTrue(optionalBook.isPresent());
    Book book = optionalBook.get();
    assertNotNull(book);
    assertEquals(title, book.getTitle());
  }

  @Test
  public void shouldNotGetBookByTitle(){
    String title = "New Domain Driven Design";
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    Optional<Book> optionalBook = sampleBookRepository.getBookByTitle(title);
    assertFalse(optionalBook.isPresent());
    Book book = optionalBook.get();
    assertNull(book);

  }
}
