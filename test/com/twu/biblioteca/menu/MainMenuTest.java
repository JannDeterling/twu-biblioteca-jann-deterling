package com.twu.biblioteca.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;

public class MainMenuTest {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayOutputStream testOut;

  @Before
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideTestInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test(expected = AssertionError.class)
  public void shouldNotRegisterInvalidMenuOptionWithNullAction() {
    MainMenu mainMenu = new MainMenu();
    mainMenu.registerMenuOption("List Books", null);
  }

  @Test(expected = AssertionError.class)
  public void shouldNotRegisterInvalidMenuOptionWithNullDescription() {
    MainMenu mainMenu = new MainMenu();
    BookService bookService = new BookService(new SampleBookRepository());
    mainMenu.registerMenuOption(null, bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldNotRegisterInvalidMenuOptionWithEmptyDescription() {
    MainMenu mainMenu = new MainMenu();
    BookService bookService = new BookService(new SampleBookRepository());
    mainMenu.registerMenuOption("", bookService::printBookList);
  }

  @Test
  public void shouldDisplayMenu() {
    provideTestInput("1");
    String description = "List Books";
    MainMenu mainMenu = new MainMenu();
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    BookService bookService = new BookService(sampleBookRepository);
    mainMenu.registerMenuOption(description, bookService::printBookList);
    mainMenu.displayMenu();

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("1) ")
        .append(description)
        .append(System.lineSeparator())
        .append("Please select a option (enter the number):")
        .append(System.lineSeparator());

    List<Book> books = sampleBookRepository.getAllBooks();
    books.forEach(book -> stringBuilder.append(book.toString()).append(System.lineSeparator()));

    assertNotEquals(testOut.toString(), "");
    assertEquals(stringBuilder.toString(), testOut.toString());
  }

  @Test
  public void shouldDisplayMenuWithRetry() {

    provideTestInput("-1 1");
    String description = "List Books";
    MainMenu mainMenu = new MainMenu();
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    BookService bookService = new BookService(sampleBookRepository);
    mainMenu.registerMenuOption(description, bookService::printBookList);
    mainMenu.displayMenu();

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("1) List Books")
        .append(System.lineSeparator())
        .append("Please select a option (enter the number):")
        .append(System.lineSeparator())
        .append("Please select a valid option!")
        .append(System.lineSeparator())
        .append("1) List Books")
        .append(System.lineSeparator())
        .append("Please select a option (enter the number):")
        .append(System.lineSeparator());

    List<Book> books = sampleBookRepository.getAllBooks();
    books.forEach(book -> stringBuilder.append(book.toString()).append(System.lineSeparator()));

    assertNotEquals(testOut.toString(), "");
    assertEquals(stringBuilder.toString(), testOut.toString());
  }
}
