package com.files.library.controller;

import com.files.library.model.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/book")
public interface BookApi {

    @GetMapping
    List<Book> getAllBooks();

    @GetMapping("/title/{title}")
    List<Book> getBookByTitle(@PathVariable String title);

    @GetMapping("/{id}")
    Book getBookById(@PathVariable Integer id);

    @PostMapping
    Book createBook(@RequestBody Book book);

    @DeleteMapping("/{id}")
    String deleteBook(@PathVariable Integer id);

    @PutMapping("/{id}")
    String modifyBook(@PathVariable Integer id, @RequestBody Book book);
}
