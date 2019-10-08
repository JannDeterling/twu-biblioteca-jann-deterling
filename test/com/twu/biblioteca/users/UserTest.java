package com.twu.biblioteca.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserTest {

    private final static String LIBRARY_NUMBER = "000-1234";
    private final static String PASSWORD = "_test123";

    @Test
    public void  shouldCreateUser(){
        User user = new User(LIBRARY_NUMBER, PASSWORD);

        assertThat(user, is(not(nullValue(User.class))));
        // should set a library number
        assertThat(user.getLibraryNumber(), is(not(nullValue(LibraryNumber.class))));
        // should have the provided libraryNumber
        assertThat(user.getLibraryNumber().toString(), is(equalTo(LIBRARY_NUMBER)));


    }

}
