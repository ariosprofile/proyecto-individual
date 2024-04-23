package com.files.library.service;

import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import java.util.List;

import java.util.Optional;

public interface BookService {

    List<BookDto> getAllBooks();
    List<BookDto> getBookByTitle(String title);
    BookDto getBookById(Integer id);
    Book createBook(BookDto bookdDto);
    String deleteBookById(Integer id);
    String modifyBookById(Integer id, BookDto modifiedBook);
}
