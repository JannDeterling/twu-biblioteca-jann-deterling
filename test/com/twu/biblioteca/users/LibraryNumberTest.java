package com.twu.biblioteca.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LibraryNumberTest {

    @Test
    public void shouldCreateLibraryNumber(){
        final String validLibNumber = "012-3456";
        final LibraryNumber libraryNumber = new LibraryNumber(validLibNumber);

        String libNumber = libraryNumber.toString();
        assertThat(libNumber.length(), is(equalTo(8)));
        assertThat(libNumber, containsString("-"));
        assertThat(libNumber.charAt(3), is(equalTo('-')));
    }
    
    @Test(expected = AssertionError.class)
    public  void shouldNotCreateLibraryNumberToShort(){
        final String invalidLibNumberToShort = "012-345";
        new LibraryNumber(invalidLibNumberToShort);
    }

    @Test(expected = AssertionError.class)
    public  void shouldNotCreateLibraryNumberNoHyphen(){
        final String invalidLibNumberNoHyphen = "01234567";
        new LibraryNumber(invalidLibNumberNoHyphen);
    }

    @Test(expected = AssertionError.class)
    public  void shouldNotCreateLibraryNumberWrongHyphen(){
        final String invalidHyphen = "0123-456";
        new LibraryNumber(invalidHyphen);
    }
}
