package com.twu.biblioteca.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;

public class MenuOptionTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @BeforeClass
  public static void setupTest() {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void shouldCreateValidMenuOption() {
    String menuOptionDescription = "List Books";
    BookService bookService = new BookService(new SampleBookRepository());
    MenuOption menuOption = new MenuOption(0, menuOptionDescription, bookService::printBookList);
    assertNotNull(menuOption);
    assertEquals(menuOption.getIndex(), new Integer(0));
    assertEquals(menuOption.getDescription(), menuOptionDescription);
  }

  @Test
  public void runMenuOptionAction(){
    String menuOptionDescription = "List Books";
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    BookService bookService = new BookService(sampleBookRepository);
    MenuOption menuOption = new MenuOption(0, menuOptionDescription, bookService::printBookList);
    menuOption.runAction();
    assertNotEquals(outContent.toString(), "");


    List<Book> books = sampleBookRepository.getAllBooks();
    final StringBuilder stringBuilder = new StringBuilder();
    books.forEach(book -> stringBuilder.append(book.toString()).append(System.lineSeparator()));

    assertEquals(outContent.toString(), stringBuilder.toString());
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithEmptyDescription() {
    BookService bookService = new BookService(new SampleBookRepository());
    new MenuOption(0, "", bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNullDescription() {
    BookService bookService = new BookService(new SampleBookRepository());
    new MenuOption(0, null, bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNullAction() {
    BookService bookService = new BookService(new SampleBookRepository());
    new MenuOption(0, "List Books", null);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNullInteger() {
    BookService bookService = new BookService(new SampleBookRepository());
    new MenuOption(null, "List Books", bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNegativeInteger() {
    BookService bookService = new BookService(new SampleBookRepository());
    new MenuOption(-1, "List Books", bookService::printBookList);
  }
}
