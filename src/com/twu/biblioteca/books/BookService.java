package com.twu.biblioteca.books;

import com.twu.biblioteca.core.LibraryItemRepository;
import com.twu.biblioteca.core.Repository;
import com.twu.biblioteca.users.LoggedInUserSingleton;
import com.twu.biblioteca.users.User;
import com.twu.biblioteca.users.UserService;

import java.util.Optional;
import java.util.Scanner;

public class BookService {

  private final LibraryItemRepository<Book> bookRepository;
  private final UserService userService;

  public BookService(LibraryItemRepository<Book> bookRepository, UserService userService) {
    this.userService = userService;
    this.bookRepository = bookRepository;
  }

  public void printBookList() {
    this.bookRepository.getAll().forEach(book -> System.out.println(book.toString()));
  }

  public void printAvailableBookList() {
    this.bookRepository.getAllAvailable().forEach(book -> System.out.println(book.toString()));
  }

  public void checkOutBook() {
    this.printAvailableBookList();
    final Optional<User> userOptional = this.userService.getLoggedInUser();
    if(userOptional.isPresent()){
      final Optional<Book> optionalBook = this.findBookToCheckOut();

      final String errorMessage = "Sorry, that book is not available.";
      if (optionalBook.isPresent()){
        Book book = optionalBook.get();
        if (book.checkOutBook(userOptional.get())) {
          System.out.println("Thank you! Enjoy the book.");
        }else {
          System.out.println(errorMessage);
        }
      }else {
        System.out.println(errorMessage);
      }
    }else {
      System.out.println("Please try again!");
    }
  }

  public void returnBook(){

    final Optional<User> userOptional = this.userService.getLoggedInUser();
    if(userOptional.isPresent()) {
      final Optional<Book> optionalBook = this.findBookToReturn();
      final String errorMessage = "Sorry, that book is not a valid book to return.";
      if (optionalBook.isPresent()) {
        Book book = optionalBook.get();
        if (book.returnBook(userOptional.get())) {
          System.out.println("Thank you for returning the book.");
        } else {
          System.out.println(errorMessage);
        }
      } else {
        System.out.println(errorMessage);
      }
    }else {
      System.out.println("Please try again!");
    }
  }


  private Optional<Book> findBookToCheckOut() {
    return this.findBookForUsageAction("Please enter the title of the book you want to check out:");
  }

  private Optional<Book> findBookToReturn() {
    return this.findBookForUsageAction("Please enter the title of the book you want to return:");
  }

  private Optional<Book> findBookForUsageAction(String messageToDisplay){
    final Scanner scanner = new Scanner(System.in);
    System.out.println(messageToDisplay);
    String title = scanner.nextLine();
    return this.bookRepository.getOneByTitle(title);
  }
}
