package com.twu.biblioteca.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;

public class MainMenuTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final ByteArrayInputStream inContent = new ByteArrayInputStream("1".getBytes());

  @BeforeClass
  public static void setupTest() {
    System.setOut(new PrintStream(outContent));
    System.setIn(inContent);
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

    assertNotEquals(outContent.toString(), "");
    assertEquals(stringBuilder.toString(), outContent.toString());
  }
}
