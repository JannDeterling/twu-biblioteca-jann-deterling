package com.twu.biblioteca.books;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class Book {

  private String title;


  public Book(String title) {
    assert title!= null : "Title should not be null";
    assert !title.equals("") : "Title should not be empty";
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }

  @Override
  public String toString() {
    return "Book{" +
        "title='" + title + '\'' +
        '}';
  }
}
