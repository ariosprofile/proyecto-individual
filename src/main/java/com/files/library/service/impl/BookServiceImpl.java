package com.files.library.service.impl;

import com.files.library.model.domain.Book;
import com.files.library.model.domain.Lease;
import com.files.library.repository.BookRepository;
import com.files.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Book getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public String deleteBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            return "Couldn't find the book with id: " + id;
        } else {
            bookRepository.delete(book.get());
            return "Book successfully deleted.";
        }
    }

    @Override
    public String modifyBookById(Integer id, Book modifiedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAuthor(modifiedBook.getAuthor());
            book.setGenre(modifiedBook.getGenre());
            book.setTitle(modifiedBook.getTitle());
            book.setSynopsis(modifiedBook.getSynopsis());
            book.setStockTypes(modifiedBook.getStockTypes());
            bookRepository.save(book);
            return "Book with id " + id + " successfully updated.";
        } else {
            return "Book with id " + id + " does not exist in our DB. Please, type an existent id.";
        }
    }
}
