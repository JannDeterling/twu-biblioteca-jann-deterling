package com.twu.biblioteca.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserTest {



    @Test
    public void  shouldCreateUser(){

        final String libraryNumber = "000-1234";
        final String password = "_test123";
        User user = new User(libraryNumber, password);

        assertThat(user, is(not(nullValue(User.class))));
        // should set a library number
        assertThat(user.getLibraryNumber(), is(not(nullValue(LibraryNumber.class))));
        // should have the provided libraryNumber
        assertThat(user.getLibraryNumber().toString(), is(equalTo(libraryNumber)));

    }

    @Test
    public void  shouldLoginUser(){

        final String libraryNumber = "000-1234";
        final String password = "_test123";
        User user = new User(libraryNumber, password);

        assertThat(user, is(not(nullValue(User.class))));
        assertThat(user.login(password), is(true));
        assertThat(user.isLoggedin(), is(true));
    }

}
