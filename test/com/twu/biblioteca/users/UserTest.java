package com.twu.biblioteca.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserTest {



    @Test
    public void  shouldCreateUser(){

        final String libraryNumber = "000-1234";
        final String password = "_test123";
        final String name = "Max";
        final String email = "max@test.de";
        final String phone = "012312123123";
        User user = new User(libraryNumber, password, name, email, phone);

        assertThat(user, is(not(nullValue(User.class))));
        // should set a library number
        assertThat(user.getLibraryNumber(), is(not(nullValue(LibraryNumber.class))));
        // should have the provided libraryNumber
        assertThat(user.getLibraryNumber().toString(), is(equalTo(libraryNumber)));
        assertThat(user.getName(), is(equalTo(name)));
        assertThat(user.getEmail(), is(equalTo(email)));
        assertThat(user.getPhone(), is(equalTo(phone)));

    }

    @Test
    public void  shouldLoginUser(){

        final String libraryNumber = "000-1234";
        final String password = "_test123";
        final String name = "Max";
        final String email = "max@test.de";
        final String phone = "012312123123";
        User user = new User(libraryNumber, password, name, email, phone);

        assertThat(user, is(not(nullValue(User.class))));
        assertThat(user.isLoggedIn(), is(false));
        assertThat(user.login("test123"), is(false));
        assertThat(user.isLoggedIn(), is(false));
        assertThat(user.login(password), is(true));
        assertThat(user.isLoggedIn(), is(true));
    }

}
