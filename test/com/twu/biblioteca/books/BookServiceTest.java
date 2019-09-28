package com.twu.biblioteca.books;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class BookServiceTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @BeforeClass
  public static void setupTest() {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void shouldPrintAllBooksFromTheRepository() {
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    List<Book> books = sampleBookRepository.getAllBooks();
    final StringBuilder stringBuilder = new StringBuilder();
    books.forEach(book -> stringBuilder.append(book.toString()).append(System.lineSeparator()));

    BookService bookService = new BookService(sampleBookRepository);
    bookService.printBookList();

    assertEquals(outContent.toString(), stringBuilder.toString());
  }

}
