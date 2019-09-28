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
    String expectedOutput = books.get(0).toString()+System.lineSeparator()
            +books.get(1).toString()+System.lineSeparator()
            +books.get(2).toString()+System.lineSeparator();

    BookService bookService = new BookService(sampleBookRepository);
    bookService.printBookList();

    assertEquals(outContent.toString(), expectedOutput);
  }

}
