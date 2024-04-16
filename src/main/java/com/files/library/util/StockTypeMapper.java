package com.files.library.util;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.StockType;

import java.util.ArrayList;
import java.util.List;

public class StockTypeMapper {

    public static StockTypeDto stockTypeMapperEntityToDto(StockType stockType) {
        return StockTypeDto.builder()
                .type(stockType.getType())
                .stock(stockType.getStock())
                .costPerDay(stockType.getCostPerDay())
                .bookDto(BookMapper.BookMapperEntityToDto(stockType.getBook()))
                .leases(LeaseMapper.mapLeasesFromEntityToDto(stockType.getLeases()))
                .build();
    }

    public static StockType stockTypeMapperDtoToEntity(StockTypeDto stockTypeDto){
        return StockType.builder()
                .type(stockTypeDto.getType())
                .stock(stockTypeDto.getStock())
                .costPerDay(stockTypeDto.getCostPerDay())
                .book(BookMapper.BookMapperDtoToEntity(stockTypeDto.getBookDto()))
                .leases(LeaseMapper.mapLeasesFromDtoToEntity(stockTypeDto.getLeases()))
                .build();
    }

    public static List<StockTypeDto> mapStockTypesFromEntityToDto(List<StockType> stockTypes) {
        List<StockTypeDto> stockTypesDtos = new ArrayList<>();

        for (StockType entityStockType : stockTypes) {
            stockTypesDtos.add(stockTypeMapperEntityToDto(entityStockType));
        }
        return stockTypesDtos;
    }

    public static List<StockType> mapStockTypesFromDtoToEntity(List<StockTypeDto> stockTypeDtos){
        List<StockType> stockTypes = new ArrayList<>();

        for (StockTypeDto dtoStockType : stockTypeDtos) {
            stockTypes.add(stockTypeMapperDtoToEntity(dtoStockType));
        }
        return stockTypes;
    }
}
