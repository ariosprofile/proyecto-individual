package com.files.library.util;

import com.files.library.model.BookDto;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.StockType;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static BookDto BookMapperEntityToDto(Book book){
        return BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .synopsis(book.getSynopsis())
                .stockTypes(StockTypeMapper.mapStockTypesFromEntityToDto(book.getStockTypes()))
                .build();
    }

    public static Book BookMapperDtoToEntity(BookDto bookDto){
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .genre(bookDto.getGenre())
                .synopsis(bookDto.getSynopsis())
                .stockTypes(StockTypeMapper.mapStockTypesFromDtoToEntity(bookDto.getStockTypes()))
                .build();
    }
}

