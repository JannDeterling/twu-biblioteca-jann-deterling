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

    public void printLoggedInUserInformation(){
        Optional<User> userOptional = LoggedInUserSingleton.getInstance().getLoggedInUser();
        if (userOptional.isPresent() && userOptional.get().isLoggedIn()){
            User user = userOptional.get();
            System.out.println(user.toString());
        }
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

    public Optional<User> getLoggedInUser() {
        Optional<User> userOptional = LoggedInUserSingleton.getInstance().getLoggedInUser();
        if (userOptional.isPresent()) {
            return userOptional;
        } else {
            this.loginUser();
            return LoggedInUserSingleton.getInstance().getLoggedInUser();
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
            LoggedInUserSingleton.getInstance().setLoggedInUser(Optional.of(user));
        } else  {
            System.out.println("The entered password is incorrect.");
        }
    }
}
