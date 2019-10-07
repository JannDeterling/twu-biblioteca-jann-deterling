package com.twu.biblioteca.books;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class SampleBookRepositoryTest {

  @Test
  public void shouldReturnListOfAllAvailableBooks() {
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    List<Book> bookList = sampleBookRepository.getAllAvailable();
    assertThat(bookList, is(not(nullValue(List.class))));
    assertThat(bookList.size(), is(not(equalTo(0))));
    assertThat(bookList.size(), is(equalTo(3)));

    bookList.get(0).checkOutBook();
    bookList = sampleBookRepository.getAllAvailable();
    assertThat(bookList, is(not(nullValue(List.class))));
    assertThat(bookList.size(), is(not(equalTo(0))));
    assertThat(bookList.size(), is(equalTo(2)));
  }

  @Test
  public void shouldReturnListOfAllBooks() {
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    List<Book> bookList = sampleBookRepository.getAll();

    assertThat(bookList, is(not(nullValue(List.class))));
    assertThat(bookList.size(), is(not(equalTo(0))));
    assertThat(bookList.size(), is(equalTo(3)));
  }

  @Test
  public void shouldGetBookByTitle(){
    String title = "Domain Driven Design";
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    Optional<Book> optionalBook = sampleBookRepository.getOneByTitle(title);
    assertTrue(optionalBook.isPresent());
    Book book = optionalBook.get();
    assertThat(book, is(not(nullValue(Book.class))));
    assertThat(title, is(equalTo(book.getTitle())));
  }

  @Test
  public void shouldNotGetBookByTitle(){
    String title = "New Domain Driven Design";
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    Optional<Book> optionalBook = sampleBookRepository.getOneByTitle(title);
    assertThat(optionalBook.isPresent(), is(false));
  }
}
