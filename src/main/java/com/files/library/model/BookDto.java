package com.files.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BookDto {

    private String author;
    private String  genre;
    private String title;
    private String synopsis;
    private List<StockTypeDto> bookCopies;
}
