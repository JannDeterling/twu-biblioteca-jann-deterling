package com.twu.biblioteca.users;

import java.util.Optional;
import java.util.Scanner;

public class UserService {

    private final SampleUserRepository sampleUserRepository;

    public UserService(SampleUserRepository sampleUserRepository){
        this.sampleUserRepository = sampleUserRepository;
    }

    public void loginUser(){
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your library number:");
        final String libraryNumber = scanner.nextLine();
        Optional<User> userOptional = this.sampleUserRepository.findUserByLibraryNumber(libraryNumber);
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            System.out.println("Please enter your password:");
            final String password = scanner.nextLine();
            if(user.login(password)){
                System.out.println("Your are now logged in!");
            }
        }
    }

}
