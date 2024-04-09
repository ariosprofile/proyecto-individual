package com.files.library.service;

import com.files.library.model.domain.Book;
import java.util.List;

import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    List<Book> getBookByTitle(String title);
    Book getBookById(Integer id);
    Book createBook(Book book);
    String deleteBookById(Integer id);
    String modifyBookById(Integer id, Book modifiedBook);
}
