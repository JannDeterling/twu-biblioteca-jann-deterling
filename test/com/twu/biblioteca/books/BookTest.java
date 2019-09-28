package com.twu.biblioteca.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BookTest {

  @Test
  public void shouldCreateValidBook(){
    String title = "Domain Driven Design";
    Book book = new Book(title);
    assertNotNull(book);
    assertEquals(title, book.getTitle());
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithNull(){
    Book book = new Book(null);
  }

  @Test(expected = AssertionError.class)
  public void shouldNotCreateValidBookWithEmptyTitle(){
    Book book = new Book("");
  }

}
