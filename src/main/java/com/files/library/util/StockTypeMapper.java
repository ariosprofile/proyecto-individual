package com.files.library.util;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.StockType;
import com.files.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockTypeMapper {

    @Autowired
    private BookRepository bookRepository;

    public static StockTypeDto stockTypeMapperEntityToDto(StockType stockType) {
        return StockTypeDto.builder()
                .id(stockType.getId())
                .type(stockType.getType())
                .stock(stockType.getStock())
                .costPerDay(stockType.getCostPerDay())
                .bookId(stockType.getBook().getId())
                .leasesIds(LeaseMapper.mapLeasesFromEntityToDto(stockType.getLeases()))
                .build();
    }

    public static StockType stockTypeMapperDtoToEntity(StockTypeDto stockTypeDto){

        if (stockTypeDto == null) {
            return null;
        }

        return StockType.builder()
                .type(stockTypeDto.getType())
                .stock(stockTypeDto.getStock())
                .costPerDay(stockTypeDto.getCostPerDay())
                .build();
    }

    public static List<Integer> mapStockTypesFromEntityToDto(List<StockType> stockTypes) {
        List<Integer> stockTypesIds = new ArrayList<>();

        for (StockType entityStockType : stockTypes) {
            stockTypesIds.add(entityStockType.getId());
        }
        return stockTypesIds;
    }

    public static List<StockType> mapStockTypesFromDtoToEntity(List<StockTypeDto> stockTypeDtos){

        if (stockTypeDtos == null) {
            return Collections.emptyList();
        }

        List<StockType> stockTypes = new ArrayList<>();

        for (StockTypeDto dtoStockType : stockTypeDtos) {
            stockTypes.add(stockTypeMapperDtoToEntity(dtoStockType));
        }
        return stockTypes;
    }
}
