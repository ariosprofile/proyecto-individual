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
        HttpHeaders headers = new HttpHeaders();
        int countOfExistingBooks = booksDto.size();
        headers.add("Existing books", Integer.toString(countOfExistingBooks));

        return new ResponseEntity<>(booksDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBookByTitle(String title) {
        List<BookDto> booksDto = bookService.getBookByTitle(title);
        HttpHeaders headers = new HttpHeaders();
        int countOfExistingBooks = booksDto.size();
        headers.add("Existing books", Integer.toString(countOfExistingBooks));

        return new ResponseEntity<>(booksDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBooksByGenre(String genre) {
        List<BookDto> booksDto =  bookService.getBooksByGenre(genre);
        HttpHeaders headers = new HttpHeaders();
        int countOfExistingBooks = booksDto.size();
        headers.add("Existing books (" + genre + ")", Integer.toString(countOfExistingBooks));

        return new ResponseEntity<>(booksDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<BookDto>> getBooksByAuthor(String author) {
        List<BookDto> booksDto =  bookService.getBooksByAuthor(author);
        HttpHeaders headers = new HttpHeaders();
        int countOfExistingBooks = booksDto.size();
        headers.add("Existing books", Integer.toString(countOfExistingBooks));

        return new ResponseEntity<>(booksDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<BookDto>  getBookById(Integer id) {
        BookDto bookDto = bookService.getBookById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Book id", bookDto.getId().toString());

        return new ResponseEntity<>(bookDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<BookDto> createBook(BookDto book) {
        BookDto newBook = bookService.createBook(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Created id", newBook.getId().toString());
        return new ResponseEntity<>(newBook, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteBook(Integer id) {
        String deleteMessage =  bookService.deleteBookById(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> modifyBook(Integer id, BookDto book) {
        String modifyMessage = bookService.modifyBookById(id, book);
        return new ResponseEntity<>(modifyMessage, HttpStatus.ACCEPTED);
    }
}
