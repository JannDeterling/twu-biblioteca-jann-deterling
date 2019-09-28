package com.twu.biblioteca.books;

import java.time.Year;

public class Book {

  private String title;
  private String author;
  private Year publishedYear;


  public Book(String title, String author, Year publishedYear) {
    this.setTitle(title);
    this.setAuthor(author);
    this.setPublishedYear(publishedYear);
  }

  private void setTitle(String title) {
    assert title!= null : "Title should not be null";
    assert !title.equals("") : "Title should not be empty";
    this.title = title;
  }

  private void setAuthor(String author) {
    assert author!= null : "Author should not be null";
    assert !author.equals("") : "Author should not be empty";
    this.author = author;
  }

  private void setPublishedYear(Year publishedYear) {
    assert publishedYear!= null : "PublishedYear should not be null";
    this.publishedYear = publishedYear;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public Year getPublishedYear() {
    return publishedYear;
  }

  @Override
  public String toString() {
    return "Book{" +
        "title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", publishedYear=" + publishedYear +
        '}';
  }
}
