package com.files.library.controller.impl;

import com.files.library.controller.BookApi;
import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import com.files.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService bookService;

    @Override
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public List<BookDto> getBookByTitle(String title) {
        return bookService.getBookByTitle(title);
    }

    @Override
    public BookDto getBookById(Integer id) {
        return bookService.getBookById(id);
    }

    @Override
    public Book createBook(BookDto book) {
        return bookService.createBook(book);
    }

    @Override
    public String deleteBook(Integer id) {
        return bookService.deleteBookById(id);
    }

    @Override
    public String modifyBook(Integer id, Book book) {
        return bookService.modifyBookById(id, book);
    }
}
