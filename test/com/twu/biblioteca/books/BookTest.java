package com.twu.biblioteca.books;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.time.Year;

public class BookTest {

  @Test
  public void shouldCreateValidBook() {
    String title = "Domain Driven Design";
    String author = "Eric Evans";
    Year publishedYear = Year.parse("2003");
    Book book = new Book(title, author, publishedYear);
    assertThat(book, is(not(nullValue(Book.class))));
    assertThat(title, is(equalTo(book.getTitle())));
    assertThat(author, is(equalTo(book.getAuthor())));
    assertThat(publishedYear, is(equalTo(book.getPublishedYear())));
    assertThat(book.isCheckedOut(), is(false));
  }

  @Test
  public void shouldCheckOutABook() {
    String title = "Domain Driven Design";
    String author = "Eric Evans";
    Year publishedYear = Year.parse("2003");
    Book book = new Book(title, author, publishedYear);
    assertThat(book, is(not(nullValue(Book.class))));
    assertThat(book.isCheckedOut(), is(false));
    assertThat(book.checkOutBook(), is(true));
    assertThat(book.isCheckedOut(), is(true));
  }

  @Test
  public void shouldNotDoubleCheckOutABook() {
    String title = "Domain Driven Design";
    String author = "Eric Evans";
    Year publishedYear = Year.parse("2003");
    Book book = new Book(title, author, publishedYear);
    assertThat(book, is(not(nullValue(Book.class))));
    assertThat(book.checkOutBook(), is(true));
    assertThat(book.isCheckedOut(), is(true));
    assertThat(book.checkOutBook(), is(false));
    assertThat(book.isCheckedOut(), is(true));

  }

  @Test
  public void shouldReturnABook() {
    String title = "Domain Driven Design";
    String author = "Eric Evans";
    Year publishedYear = Year.parse("2003");
    Book book = new Book(title, author, publishedYear);
    assertThat(book, is(not(nullValue(Book.class))));
    assertThat(book.isCheckedOut(), is(false));
    assertThat(book.checkOutBook(), is(true));
    assertThat(book.isCheckedOut(), is(true));
    assertThat(book.returnBook(), is(true));
    assertThat(book.isCheckedOut(), is(false));
  }

  @Test
  public void shouldReturnAAvailableBook() {
    String title = "Domain Driven Design";
    String author = "Eric Evans";
    Year publishedYear = Year.parse("2003");
    Book book = new Book(title, author, publishedYear);
    assertThat(book, is(not(nullValue(Book.class))));
    assertThat(book.returnBook(), is(false));
    assertThat(book.isCheckedOut(), is(false));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNull() {
    new Book(null, null, null);
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithEmptyTitle() {
    new Book("", "Eric Evans", Year.parse("2003"));
  }


  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNullTitle() {
    new Book(null, "Eric Evans", Year.parse("2003"));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithEmptyAuthor() {
    new Book("Domain Driven Design", "", Year.parse("2003"));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNullAuthor() {
    new Book("Domain Driven Design", null, Year.parse("2003"));
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNullYear() {
   new Book("Domain Driven Design", "Eric Evans", null);
  }
}
