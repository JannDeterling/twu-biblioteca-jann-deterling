package com.twu.biblioteca.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LibraryNumberTest {

    @Test
    public void shouldGenerateLibraryNumber(){
        LibraryNumber libraryNumber = new LibraryNumber();

        String libNumber = libraryNumber.toString();
        assertThat(libNumber.length(), is(equalTo(8)));
        assertThat(libNumber, containsString("-"));
        assertThat(libNumber.charAt(3), is(equalTo('-')));
    }
}
