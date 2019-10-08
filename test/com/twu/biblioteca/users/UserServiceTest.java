package com.twu.biblioteca.users;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.twu.biblioteca.books.SampleBookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;

public class UserServiceTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOutput;

    @Before
    public void setUpOutput() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
        LoggedInUserSingleton.getInstance().setLoggedInUser(Optional.empty());
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

        provideTestInput(libraryNumber+"\n"+password);
        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        userService.loginUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().isPresent(), is(true));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().get().getLibraryNumber().toString(), is(equalTo(libraryNumber)));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().get().isLoggedIn(), is(equalTo(true)));
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

        provideTestInput(libraryNumber+"\n"+password);
        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        userService.loginUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().isPresent(), is(false));
    }

    @Test
    public void shouldNotLogInUserWrongUser(){
        final String libraryNumber = "045-3456";
        final StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter your library number:")
                .append(System.lineSeparator())
                .append("The entered library number is not valid.")
                .append(System.lineSeparator());

        provideTestInput(libraryNumber);
        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        userService.loginUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().isPresent(), is(false));
    }

    @Test
    public void shouldReturnTheLoggedInUser(){
        final String libraryNumber = "012-3456";
        final String password = "_test1234";
        final StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Please enter your library number:")
                .append(System.lineSeparator())
                .append("Please enter your password:")
                .append(System.lineSeparator())
                .append("Your are now logged in!")
                .append(System.lineSeparator());

        provideTestInput(libraryNumber+"\n"+password);
        SampleUserRepository sampleUserRepository = new SampleUserRepository();
        UserService userService = new UserService(sampleUserRepository);
        Optional<User> userOptional = userService.getLoggedInUser();
        assertThat(testOutput.toString(), is(equalTo(expectedOutput.toString())));
        assertThat(userOptional.isPresent(), is(true));
        assertThat(userOptional.get(), is(notNullValue()));
        assertThat(userOptional.get().getLibraryNumber().toString(), is(equalTo(libraryNumber)));
        assertThat(userOptional.get().isLoggedIn(), is(true));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().isPresent(), is(true));
        assertThat(LoggedInUserSingleton.getInstance().getLoggedInUser().get(), is(equalTo(userOptional.get())));
    }

    @Test
    public void shouldPrintPersonalInformationOfLoggedInUser(){
        User user = new User("012-3456", "test1234", "max", "max.mustermann@test.de", "0123919123");
        user.login("test1234");
        LoggedInUserSingleton.getInstance().setLoggedInUser(Optional.of(user));
        UserService userService = new UserService(new SampleUserRepository());
        userService.printLoggedInUserInformation();
        assertThat(testOutput.toString(), is(equalTo(user.toString()+System.lineSeparator())));
    }

    private void provideTestInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}
