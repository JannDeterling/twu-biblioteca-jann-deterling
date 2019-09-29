package com.twu.biblioteca.books;

import java.util.Optional;
import java.util.Scanner;

public class BookService {

  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository){
    this.bookRepository = bookRepository;
  }

  public void printBookList(){
    this.bookRepository.getAllBooks().forEach(book -> System.out.println(book.toString()));
  }

  public void checkOutBook() {
    final Scanner scanner = new Scanner(System.in);
    this.printBookList();
    System.out.println("Please enter the title of the book you want to check out:");
    String title = scanner.next();
    Optional<Book> optionalBook = this.bookRepository.getBookByTitle(title);
  }

}
