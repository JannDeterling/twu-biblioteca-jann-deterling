package com.twu.biblioteca.users;

public class User {

    private final LibraryNumber libraryNumber;
    private final String name;
    private final String phone;
    private final String email;
    // never store password as string
    private final String password;
    private Boolean isLoggedIn;

    public User(String libraryNumber, String password ,String name, String email, String phone) {
        this(new LibraryNumber(libraryNumber), password, name, email, phone);
    }

    public User(LibraryNumber libraryNumber, String password, String name, String email, String phone) {
        this.libraryNumber = libraryNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
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

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "libraryNumber=" + libraryNumber +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
