package com.twu.biblioteca.users;

import com.twu.biblioteca.core.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SampleUserRepository  implements Repository<User> {

    private  final List<User> SAMPLE_USERS = new ArrayList<User>(Arrays.asList(
            new User("012-3456", "_test1234"),
            new User("123-4567", "_qwert123")
    ));


    @Override
    public List<User> getAll() {
        return SAMPLE_USERS;
    }

    Optional<User> findUserByLibraryNumber(String libraryNumber) {
        return this.SAMPLE_USERS.stream().filter(user -> user.getLibraryNumber().toString().equals(libraryNumber)).findFirst();
    }

}
