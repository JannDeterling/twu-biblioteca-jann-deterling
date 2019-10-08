package com.twu.biblioteca.users;

import java.util.Optional;

public final class LoggedInUserSingleton {

    private static final LoggedInUserSingleton INSTANCE = new LoggedInUserSingleton();
    private Optional<User> loggedInUser;


    private LoggedInUserSingleton(){
        this.loggedInUser = Optional.empty();
    }

    public static LoggedInUserSingleton getInstance() {
        return INSTANCE;
    }

    public void setLoggedInUser(Optional<User> loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Optional<User> getLoggedInUser() {
        return loggedInUser;
    }
}
