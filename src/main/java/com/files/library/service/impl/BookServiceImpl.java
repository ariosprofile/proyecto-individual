package com.files.library.service.impl;

import com.files.library.model.BookDto;
import com.files.library.model.LeaseDto;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.StockType;
import com.files.library.repository.BookRepository;
import com.files.library.repository.StockTypeRepository;
import com.files.library.service.BookService;
import com.files.library.util.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StockTypeRepository stockTypeRepository;

    @Override
    public List<BookDto> getAllBooks() {

        List<BookDto> bookDtos = new ArrayList<>();

        for (Book entityBook : bookRepository.findAll()) {
            bookDtos.add(BookMapper.BookMapperEntityToDto(entityBook));
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> getBookByTitle(String title) {

        List<BookDto> bookDtos = new ArrayList<>();

        for (Book entityBook : bookRepository.findByTitleContainingIgnoreCase(title)) {
            bookDtos.add(BookMapper.BookMapperEntityToDto(entityBook));
        }
        return bookDtos;
    }

    @Override
    public BookDto getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()){
            return BookMapper.BookMapperEntityToDto(book.get());
        } else {
            throw new EntityNotFoundException("Book with id " + id + "does not exists in our DB.");
        }
    }

    @Override
    public Book createBook(BookDto bookDto) {
        return bookRepository.save(BookMapper.BookMapperDtoToEntity(bookDto));
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
    public String modifyBookById(Integer id, BookDto modifiedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAuthor(modifiedBook.getAuthor());
            book.setGenre(modifiedBook.getGenre());
            book.setTitle(modifiedBook.getTitle());
            book.setSynopsis(modifiedBook.getSynopsis());

            List<StockType> stockTypes = new ArrayList<>();

            for (Integer stockTypeId : modifiedBook.getStockTypesIds()) {
                Optional<StockType> stockType = stockTypeRepository.findById(stockTypeId);

                stockTypes.add(stockType.orElseThrow());
            }

            book.setStockTypes(stockTypes);
            bookRepository.save(book);
            return "Book with id " + id + " successfully updated.";
        } else {
            return "Book with id " + id + " does not exist in our DB. Please, type an existent id.";
        }
    }
}
