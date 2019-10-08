package com.twu.biblioteca.users;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class SampleUserRepositoryTest {

    private SampleUserRepository sampleUserRepository;

    @Before
    public void setup(){
        this.sampleUserRepository = new SampleUserRepository();
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = this.sampleUserRepository.getAll();
        assertThat(users, is(notNullValue()));
        assertThat(users.size(), is(not(0)));
        assertThat(users.size(), is(2));
    }

    @Test
    public  void shouldReturnOneUserByLibNumber(){
        String libNumber = "012-3456";
        Optional<User> userOptional = this.sampleUserRepository.findUserByLibraryNumber(libNumber);
        assertThat(userOptional.isPresent(), is(true));
        assertThat(userOptional.get(), is(notNullValue()));
        assertThat(userOptional.get().getLibraryNumber().toString(), is(equalTo(libNumber)));
    }

}
