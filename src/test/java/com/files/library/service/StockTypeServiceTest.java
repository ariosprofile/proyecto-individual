package com.files.library.service;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;
import com.files.library.repository.StockTypeRepository;
import com.files.library.service.impl.StockTypeServiceImpl;
import com.files.library.util.StockTypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class StockTypeServiceTest {

    @Mock
    private StockTypeRepository stockTypeRepository;

    @InjectMocks
    private StockTypeServiceImpl stockTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStocksByBookId_ReturnsListOfStocks() {
        // Arrange
        int bookId = 1;
        List<StockType> stocks = List.of(
                new StockType(),
                new StockType()
        );


        when(stockTypeRepository.findByBookId(bookId)).thenReturn(stocks);


        List<StockTypeDto> result = stockTypeService.getStocksByBookId(bookId);


        verify(stockTypeRepository, times(1)).findByBookId(bookId);
        verifyNoMoreInteractions(stockTypeRepository);

        assertEquals(stocks.size(), result.size());
        assertTrue(result.containsAll(stocks));
    }

    @Test
    void createStock_StockCreated_ReturnsCreatedStock() {

        StockTypeDto newStock = new StockTypeDto();
        StockType savedStock = new StockType();


        when(stockTypeRepository.save(StockTypeMapper.stockTypeMapperDtoToEntity(newStock))).thenReturn(savedStock);


        StockType result = stockTypeService.createStock(newStock);


        verify(stockTypeRepository, times(1)).save(StockTypeMapper.stockTypeMapperDtoToEntity(newStock));
        verifyNoMoreInteractions(stockTypeRepository);

        assertEquals(savedStock, result);
    }

    @Test
    void deleteStockById_StockExists_SuccessfullyDeleted() {

        int id = 1;


        when(stockTypeRepository.findById(id)).thenReturn(Optional.of(new StockType()));


        String result = stockTypeService.deleteStockById(id);


        verify(stockTypeRepository, times(1)).findById(id);
        verify(stockTypeRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(stockTypeRepository);

        assertEquals("Stock successfully deleted", result);
    }

    @Test
    void deleteStockById_StockDoesNotExist_ReturnsErrorMessage() {

        int id = 1;


        when(stockTypeRepository.findById(id)).thenReturn(Optional.empty());


        String result = stockTypeService.deleteStockById(id);


        verify(stockTypeRepository, times(1)).findById(id);
        verifyNoMoreInteractions(stockTypeRepository);

        assertEquals("Stock with id " + id + "does not exists in our DB.", result);
    }

    @Test
    void modifyStockById_StockExists_SuccessfullyUpdated() {

        int id = 1;
        StockTypeDto modifiedStock = new StockTypeDto();


        when(stockTypeRepository.findById(id)).thenReturn(Optional.of(new StockType()));


        String result = stockTypeService.modifyStockById(id, modifiedStock);


        verify(stockTypeRepository, times(1)).findById(id);
        verify(stockTypeRepository, times(1)).save(any(StockType.class));
        verifyNoMoreInteractions(stockTypeRepository);

        assertEquals("Stock with id " + id + " successfully updated.", result);
    }

    @Test
    void modifyStockById_StockDoesNotExist_ReturnsErrorMessage() {

        int id = 1;
        StockTypeDto modifiedStock = new StockTypeDto();


        when(stockTypeRepository.findById(id)).thenReturn(Optional.empty());


        String result = stockTypeService.modifyStockById(id, modifiedStock);


        verify(stockTypeRepository, times(1)).findById(id);
        verifyNoMoreInteractions(stockTypeRepository);

        assertEquals("Stock with id " + id + " does not exists in our DB. Please, type a existent id.", result);
    }
}
