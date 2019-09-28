package com.twu.biblioteca.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.time.Year;

public class BookTest {

  @Test
  public void shouldCreateValidBook() {
    String title = "Domain Driven Design";
    String author = "Eric Evans";
    Year publishedYear = Year.parse("2003");
    Book book = new Book(title, author, publishedYear);
    assertNotNull(book);
    assertEquals(title, book.getTitle());
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNull() {
    Book book = new Book(null, null, null);
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithEmptyTitle() {
    Book book = new Book("", "Eric Evans", Year.parse("2003"));
  }


  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNullTitle() {
    Book book = new Book(null, "Eric Evans", Year.parse("2003"));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithEmptyAuthor() {
    Book book = new Book("Domain Driven Design", "", Year.parse("2003"));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNullAuthor() {
    Book book = new Book("Domain Driven Design", null, Year.parse("2003"));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNullYear() {
    Book book = new Book("Domain Driven Design", "Eric Evans", null);
  }
}
