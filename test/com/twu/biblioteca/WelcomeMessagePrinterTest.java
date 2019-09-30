package com.twu.biblioteca;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WelcomeMessagePrinterTest {

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


  @BeforeClass
  public static void setupTest() {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void shouldPrintWelcomeMessageToStdOut() {
    // Add the lineSeparator because the WelcomeMessagePrinter should print the welcome message to a separate line and
    // the next content should also start on the next line!
    String expectedOutcome = "Welcome to Biblioteca. Your one-stop-shop for great book titles in "
        + "Bangalore!" + System.lineSeparator();
    WelcomeMessagePrinter.printWelcomeMessage();
    assertThat(expectedOutcome, is(equalTo(outContent.toString())));
  }
}
