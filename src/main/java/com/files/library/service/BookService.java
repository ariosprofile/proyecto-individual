package com.files.library.service;

import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import java.util.List;

import java.util.Optional;

public interface BookService {

    List<BookDto> getAllBooks();
    List<BookDto> getBookByTitle(String title);
    List<BookDto> getBooksByGenre(String genre);
    List<BookDto> getBooksByAuthor(String author);
    BookDto getBookById(Integer id);
    BookDto createBook(BookDto bookDto);
    void deleteBookById(Integer id);
    void modifyBookById(Integer id, BookDto modifiedBook);
}
