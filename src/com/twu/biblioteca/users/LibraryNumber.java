package com.twu.biblioteca.users;

public class LibraryNumber {
    private final String libraryNumber;


    public LibraryNumber(String libraryNumber){
        assert libraryNumber.length() == 8: "The length of the libraryNumber is not equal 8.";
        assert libraryNumber.contains("-"): "The libraryNumber is missing a \'-\'";
        assert libraryNumber.charAt(3) == '-':"The libraryNumber format is wrong should be xxx-xxxx";
        this.libraryNumber = libraryNumber;
    }

    @Override
    public String toString() {
        return libraryNumber;
    }
}
