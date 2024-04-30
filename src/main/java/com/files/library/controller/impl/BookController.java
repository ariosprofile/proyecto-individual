package com.files.library.controller.impl;

import com.files.library.controller.BookApi;
import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import com.files.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> booksDto =  bookService.getAllBooks();
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBookByTitle(String title) {
        List<BookDto> booksDto = bookService.getBookByTitle(title);
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBooksByGenre(String genre) {
        List<BookDto> booksDto =  bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBooksByAuthor(String author) {
        List<BookDto> booksDto =  bookService.getBooksByAuthor(author);
        return new ResponseEntity<>(booksDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDto>  getBookById(Integer id) {
        BookDto bookDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDto> createBook(BookDto book) {
        BookDto newBook = bookService.createBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteBook(Integer id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> modifyBook(Integer id, BookDto book) {
        bookService.modifyBookById(id, book);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
