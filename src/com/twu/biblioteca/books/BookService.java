package com.twu.biblioteca.books;

import java.util.Optional;
import java.util.Scanner;

public class BookService {

  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void printBookList() {
    this.bookRepository.getAllBooks().forEach(book -> System.out.println(book.toString()));
  }

  public void printAvailableBookList() {
    this.bookRepository.getAllBooksAvailable().forEach(book -> System.out.println(book.toString()));
  }

  public void checkOutBook() {
    final Scanner scanner = new Scanner(System.in);
    this.printAvailableBookList();
    System.out.println("Please enter the title of the book you want to check out:");
    String title = scanner.nextLine();
    Optional<Book> optionalBook = this.bookRepository.getBookByTitle(title);

    final String errorMessage = "Sorry, that book is not available.";
    if (optionalBook.isPresent()){
      Book book = optionalBook.get();
      if (book.checkOutBook()) {
        System.out.println("Thank you! Enjoy the book.");
      }else {
        System.out.println(errorMessage);
      }
    }else {
      System.out.println(errorMessage);
    }

  }

  public void returnBook(){
    final Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the title of the book you want to return:");
    String title = scanner.nextLine();
    Optional<Book> optionalBook = this.bookRepository.getBookByTitle(title);

    final String errorMessage = "Sorry, that book is not a valid book to return.";
    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      if (book.returnBook()) {
        System.out.println("Thank you for returning the book.");
      }else {
        System.out.println(errorMessage);
      }
    }else {
      System.out.println(errorMessage);
    }
  }

}
