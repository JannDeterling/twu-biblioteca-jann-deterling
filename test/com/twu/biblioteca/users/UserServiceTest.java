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
        final String password = "_test123";
        final StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter your library number:")
                .append(System.lineSeparator())
                .append(libraryNumber)
                .append("Please enter your password:")
                .append(System.lineSeparator())
                .append(password)
                .append("Your are now logged in!")
                .append(System.lineSeparator());


        provideTestInput(libraryNumber);
        provideTestInput(password);
        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        userService.loginUser();

        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
    }

    private void provideTestInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}
