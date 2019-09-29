package com.twu.biblioteca.books;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class BookServiceTest {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  private ByteArrayOutputStream outContent;

  @Before
  public void setUpOutput() {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void shouldPrintAllBooksFromTheRepository() {
    final String expectedOutcome = this.expectedPrintedBooks();
    BookService bookService = new BookService(new SampleBookRepository());
    bookService.printBookList();
    assertEquals(expectedOutcome, outContent.toString());
  }

  @Test
  public void shouldCheckoutABook() {
    final StringBuilder expectedOutcome = new StringBuilder();
    expectedOutcome.append(this.expectedPrintedBooks())
        .append("Please enter the title of the book you want to check out:")
        .append(System.lineSeparator())
        .append("Thank you! Enjoy the book.")
        .append(System.lineSeparator());
    this.provideTestInput("Domain Driven Design");
    BookService bookService = new BookService(new SampleBookRepository());
    bookService.checkOutBook();
    assertEquals(expectedOutcome.toString(), outContent.toString());
  }

  private void provideTestInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String expectedPrintedBooks() {
    final SampleBookRepository sampleBookRepository = new SampleBookRepository();
    final List<Book> books = sampleBookRepository.getAllBooks();
    final StringBuilder stringBuilder = new StringBuilder();
    books.forEach(book -> stringBuilder.append(book.toString()).append(System.lineSeparator()));
    return stringBuilder.toString();
  }

}
