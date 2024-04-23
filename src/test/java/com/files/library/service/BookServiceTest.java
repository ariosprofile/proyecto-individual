package com.files.library.service;

import com.files.library.model.BookDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.StockType;
import com.files.library.repository.BookRepository;
import com.files.library.service.impl.BookServiceImpl;
import com.files.library.util.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBooks() {
        List<Book> expectedBooks = new ArrayList<>();


        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<BookDto> actualBooks = bookService.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getBookByTitle() {
        String title = "Example Title";
        List<Book> expectedBooks = new ArrayList<>();


        when(bookRepository.findByTitleContainingIgnoreCase(title)).thenReturn(expectedBooks);

        List<BookDto> actualBooks = bookService.getBookByTitle(title);

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getBookById() {
        int id = 1;
        Book expectedBook = new Book();


        when(bookRepository.findById(id)).thenReturn(Optional.of(expectedBook));

        BookDto actualBook = bookService.getBookById(id);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    void createBook() {
        BookDto bookToCreate = new BookDto();


        when(bookRepository.save(BookMapper.BookMapperDtoToEntity(bookToCreate))).thenReturn(BookMapper.BookMapperDtoToEntity(bookToCreate));

        Book createdBook = bookService.createBook(bookToCreate);

        assertEquals(bookToCreate, createdBook);
    }

    @Test
    void deleteBookById() {
        int id = 1;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        String result = bookService.deleteBookById(id);

        assertTrue(result.startsWith("Couldn't find the book"));
        verify(bookRepository, times(1)).findById(id);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void modifyBookById() {
        int id = 1;
        BookDto modifiedBook = new BookDto();
        modifiedBook.setAuthor("Modified Author");
        modifiedBook.setGenre("Modified Genre");
        modifiedBook.setTitle("Modified Title");
        modifiedBook.setSynopsis("Modified Synopsis");


        List<Integer> modifiedStockTypes = new ArrayList<>();
        modifiedStockTypes.add(1);
        modifiedStockTypes.add(2);
        modifiedBook.setStockTypesIds(modifiedStockTypes);

        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setAuthor("Original Author");
        existingBook.setGenre("Original Genre");
        existingBook.setTitle("Original Title");
        existingBook.setSynopsis("Original Synopsis");


        List<StockType> existingStockTypes = new ArrayList<>();
        existingStockTypes.add(new StockType());
        existingStockTypes.add(new StockType());
        existingBook.setStockTypes(existingStockTypes);


        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));

        String result = bookService.modifyBookById(id, modifiedBook);


        verify(bookRepository, times(1)).findById(id);

        verify(bookRepository, times(1)).save(existingBook);

        verifyNoMoreInteractions(bookRepository);

        assertEquals("Book with id " + id + " successfully updated.", result);
    }
}
