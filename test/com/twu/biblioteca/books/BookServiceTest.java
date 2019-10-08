package com.twu.biblioteca.books;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.twu.biblioteca.users.LoggedInUserSingleton;
import com.twu.biblioteca.users.SampleUserRepository;
import com.twu.biblioteca.users.User;
import com.twu.biblioteca.users.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  private ByteArrayOutputStream testOutput;
  private BookService bookService;
  private SampleBookRepository sampleBookRepository;

  @Before
  public void setUpOutput() {
    this.logInASampleUser();
    UserService userService = new UserService(new SampleUserRepository());
    this.sampleBookRepository = new SampleBookRepository();
    this.bookService = new BookService(sampleBookRepository, userService);
    testOutput = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOutput));

  }

  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test
  public void shouldPrintAllBooksFromTheRepository() {
    final SampleBookRepository sampleBookRepository = new SampleBookRepository();
    final String expectedOutcome = this.expectedPrintedBooks(sampleBookRepository.getAll());
    bookService.printBookList();
    assertThat(expectedOutcome, is(equalTo(testOutput.toString())));
  }

  @Test
  public void shouldCheckoutABook() {
    final StringBuilder expectedOutput = new StringBuilder();
    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()))
        .append("Please enter the title of the book you want to check out:")
        .append(System.lineSeparator())
        .append("Thank you! Enjoy the book.")
        .append(System.lineSeparator());
    this.provideTestInput("Domain Driven Design");
    bookService.checkOutBook();
    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  @Test
  public void shouldNotCheckoutANonExistingBook() {
    final StringBuilder expectedOutput = new StringBuilder();
    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()))
        .append("Please enter the title of the book you want to check out:")
        .append(System.lineSeparator())
        .append("Sorry, that book is not available.")
        .append(System.lineSeparator());
    this.provideTestInput("New Domain Driven Design");
    bookService.checkOutBook();
    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  @Test
  public void shouldNotDoubleCheckoutABook() {
    final StringBuilder expectedOutput = new StringBuilder();

    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()))
        .append("Please enter the title of the book you want to check out:")
        .append(System.lineSeparator())
        .append("Thank you! Enjoy the book.")
        .append(System.lineSeparator());
    this.provideTestInput("Domain Driven Design");
    bookService.checkOutBook();

    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()))
        .append("Please enter the title of the book you want to check out:")
        .append(System.lineSeparator())
        .append("Sorry, that book is not available.")
        .append(System.lineSeparator());

    this.provideTestInput("Domain Driven Design");
    bookService.checkOutBook();
    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  @Test
  public void shouldReturnABook() {
    final StringBuilder expectedOutput = new StringBuilder();

    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()))
        .append("Please enter the title of the book you want to check out:")
        .append(System.lineSeparator())
        .append("Thank you! Enjoy the book.")
        .append(System.lineSeparator());
    this.provideTestInput("Domain Driven Design");
    bookService.checkOutBook();

    expectedOutput.append("Please enter the title of the book you want to return:")
        .append(System.lineSeparator())
        .append("Thank you for returning the book.")
        .append(System.lineSeparator());
    this.provideTestInput("Domain Driven Design");
    bookService.returnBook();

    bookService.printAvailableBookList();
    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()));

    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  @Test
  public void shouldNotReturnAvailableBook() {
    final StringBuilder expectedOutput = new StringBuilder();
    expectedOutput.append("Please enter the title of the book you want to return:")
        .append(System.lineSeparator())
        .append("Sorry, that book is not a valid book to return.")
        .append(System.lineSeparator());
    this.provideTestInput("Domain Driven Design");
    bookService.returnBook();

    bookService.printAvailableBookList();
    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()));
    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  @Test
  public void shouldNotReturnUnValidBook() {
    final StringBuilder expectedOutput = new StringBuilder();
    expectedOutput.append("Please enter the title of the book you want to return:")
        .append(System.lineSeparator())
        .append("Sorry, that book is not a valid book to return.")
        .append(System.lineSeparator());
    this.provideTestInput("New Domain Driven Design");
    bookService.returnBook();

    bookService.printAvailableBookList();
    expectedOutput.append(this.expectedPrintedBooks(sampleBookRepository.getAllAvailable()));
    assertThat(expectedOutput.toString(), is(equalTo(testOutput.toString())));
  }

  private void provideTestInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String expectedPrintedBooks(List<Book> books) {
    final StringBuilder stringBuilder = new StringBuilder();
    books.forEach(book -> stringBuilder.append(book.toString()).append(System.lineSeparator()));
    return stringBuilder.toString();
  }

  private void logInASampleUser(){
    User user = new User("012-3456", "test123");
    user.login("test123");
    LoggedInUserSingleton.getInstance().setLoggedInUser(Optional.of(user));
  }

}
