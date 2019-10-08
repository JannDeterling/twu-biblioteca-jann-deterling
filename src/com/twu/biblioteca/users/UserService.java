package com.twu.biblioteca.users;

import java.util.Optional;
import java.util.Scanner;

public class UserService {

    private final SampleUserRepository sampleUserRepository;
    private final Scanner scanner;

    public UserService(SampleUserRepository sampleUserRepository){
        this.scanner = new Scanner(System.in);
        this.sampleUserRepository = sampleUserRepository;
    }

    public void loginUser(){
        Optional<User> userOptional = this.getUserInteractiveByLibraryNumber();
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            this.loginUserInteractive(user);
        }else {
            System.out.println("The entered library number is not valid.");
        }
    }

    private Optional<User> getUserInteractiveByLibraryNumber() {
        System.out.println("Please enter your library number:");
        final String libraryNumber = scanner.nextLine();
        return this.sampleUserRepository.findUserByLibraryNumber(libraryNumber);
    }

    private void loginUserInteractive(User user){
        System.out.println("Please enter your password:");
        final String password = scanner.nextLine();
        if(user.login(password)){
            System.out.println("Your are now logged in!");
        } else  {
            System.out.println("The entered password is incorrect.");
        }
    }

}
