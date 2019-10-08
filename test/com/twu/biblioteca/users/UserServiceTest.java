package com.twu.biblioteca.users;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class UserServiceTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOutput;

    @Before
    public void setUpOutput() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void shouldLogInUser(){
        final String libraryNumber = "012-3456";
        final String password = "_test1234";
        final StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter your library number:")
                .append(System.lineSeparator())
                .append("Please enter your password:")
                .append(System.lineSeparator())
                .append("Your are now logged in!")
                .append(System.lineSeparator());

        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        provideTestInput(libraryNumber+"\n"+password);
        userService.loginUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
    }

    @Test
    public void shouldNotLogInUserWrongPassword(){
        final String libraryNumber = "012-3456";
        final String password = "_test12";
        final StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter your library number:")
                .append(System.lineSeparator())
                .append("Please enter your password:")
                .append(System.lineSeparator())
                .append("The entered password is incorrect.")
                .append(System.lineSeparator());

        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        provideTestInput(libraryNumber+"\n"+password);
        userService.loginUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
    }

    @Test
    public void shouldNotLogInUserWrongUser(){
        final String libraryNumber = "045-3456";
        final StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter your library number:")
                .append(System.lineSeparator())
                .append("The entered library number is not valid.")
                .append(System.lineSeparator());

        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        provideTestInput(libraryNumber);
        userService.loginUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
    }

    private void provideTestInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}
