package com.files.library.controller;

import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/book")
public interface BookApi {

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBooks();

    @GetMapping("/{title}")
    ResponseEntity<List<BookDto>> getBookByTitle(@PathVariable String title);

    @GetMapping("/genre/{genre}")
    ResponseEntity<List<BookDto>> getBooksByGenre(@PathVariable String genre);

    @GetMapping("/author/{author}")
    ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String author);

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBookById(@PathVariable Integer id);

    @PostMapping
    ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> modifyBook(@PathVariable Integer id, @RequestBody BookDto book);
}
