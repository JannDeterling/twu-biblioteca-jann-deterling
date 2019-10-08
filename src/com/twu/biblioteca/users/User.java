package com.twu.biblioteca.users;

public class User {

    private final LibraryNumber libraryNumber;
    // never store password as string
    private final String password;
    private Boolean isLoggedIn;

    public User(String libraryNumber, String password){
        this.libraryNumber = new LibraryNumber(libraryNumber);
        this.password = password;
        this.isLoggedIn = false;
    }

    public User(LibraryNumber libraryNumber, String password){
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public LibraryNumber getLibraryNumber() {
        return libraryNumber;
    }

    public Boolean login(String password){
        if (this.password.equals(password)){
            this.isLoggedIn = true;
            return true;
        }else {
            return false;
        }
    }

    public Boolean isLoggedIn(){
        return this.isLoggedIn;
    }
}
