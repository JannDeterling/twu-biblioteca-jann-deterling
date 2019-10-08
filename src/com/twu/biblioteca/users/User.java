package com.twu.biblioteca.users;

public class User {

    private final LibraryNumber libraryNumber;
    // never store password as string
    private final String password;

    public User(String libraryNumber, String password){
        this.libraryNumber = new LibraryNumber(libraryNumber);
        this.password = password;
    }

    public User(LibraryNumber libraryNumber, String password){
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public LibraryNumber getLibraryNumber() {
        return libraryNumber;
    }
}
