package com.twu.biblioteca.menu;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import com.twu.biblioteca.users.SampleUserRepository;
import com.twu.biblioteca.users.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.twu.biblioteca.books.Book;
import com.twu.biblioteca.books.BookService;
import com.twu.biblioteca.books.SampleBookRepository;

public class MenuOptionTest {

  private static final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
  private BookService bookService;

  @BeforeClass
  public static void setupTestClass() {
    System.setOut(new PrintStream(testOutput));

  }

  @Before
  public void setupTest(){
    UserService userService = new UserService(new SampleUserRepository());
    this.bookService = new BookService(new SampleBookRepository(), userService);
  }


  @Test
  public void shouldCreateValidMenuOption() {
    String menuOptionDescription = "List Books";
    MenuOption menuOption = new MenuOption(0, menuOptionDescription, bookService::printBookList);
    assertThat(menuOption, is(not(nullValue(MenuOption.class))));
    assertThat(menuOption.getIndex(), is(0));
    assertThat(menuOption.getDescription(), is(equalTo(menuOptionDescription)));
  }

  @Test
  public void runMenuOptionAction(){
    String menuOptionDescription = "List Books";
    SampleBookRepository sampleBookRepository = new SampleBookRepository();
    MenuOption menuOption = new MenuOption(0, menuOptionDescription, bookService::printBookList);
    menuOption.runAction();
    assertThat(testOutput.toString(), is(not(equalTo(""))));


    List<Book> books = sampleBookRepository.getAll();
    final StringBuilder expectedOutput = new StringBuilder();
    books.forEach(book -> expectedOutput.append(book.toString()).append(System.lineSeparator()));

    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithEmptyDescription() {
    new MenuOption(0, "", bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNullDescription() {
    new MenuOption(0, null, bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNullAction() {
    new MenuOption(0, "List Books", null);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNullInteger() {
    new MenuOption(null, "List Books", bookService::printBookList);
  }

  @Test(expected = AssertionError.class)
  public void shouldCreateInvalidMenuOptionWithNegativeInteger() {
    new MenuOption(-1, "List Books", bookService::printBookList);
  }
}
