package com.files.library.controller;

import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/book")
public interface BookApi {

    @GetMapping
    List<BookDto> getAllBooks();

    @GetMapping("/title/{title}")
    List<BookDto> getBookByTitle(@PathVariable String title);

    @GetMapping("/{id}")
    BookDto getBookById(@PathVariable Integer id);

    @PostMapping
    Book createBook(@RequestBody BookDto bookDto);

    @DeleteMapping("/{id}")
    String deleteBook(@PathVariable Integer id);

    @PutMapping("/{id}")
    String modifyBook(@PathVariable Integer id, @RequestBody Book book);
}
