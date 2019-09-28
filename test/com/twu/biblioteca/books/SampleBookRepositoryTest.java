package com.twu.biblioteca.books;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.List;

public class SampleBookRepositoryTest {

  @Test
  public void shouldReturnListOfAllBooks() {
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    List<Book> bookList = sampleBookRepository.getAllBooks();

    assertNotNull(bookList);
    assertNotEquals(bookList.size(), 0);
    assertEquals(bookList.size(), 3);
  }

}
