package com.twu.biblioteca.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserTest {

    private final static String USER_NAME = "JANN";

    @Test
    public void  shouldCreateUser(){
        User user = new User(USER_NAME);

        assertThat(user.getLibraryNumber(), is(not(nullValue(User.class))));
        // should generate a library number
        assertThat(user.getLibraryNumber(), is(not(nullValue(LibraryNumber.class))));
        // should have the provided name
        assertThat(user.getName(), is(equalTo(USER_NAME)));
    }

}
