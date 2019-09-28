package com.twu.biblioteca.books;

public class BookService {

  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository){
    this.bookRepository = bookRepository;
  }

  public void printBookList(){
    this.bookRepository.getAllBooks().forEach(book -> System.out.println(book.toString()));
  }

}
