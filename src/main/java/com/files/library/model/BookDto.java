package com.files.library.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Integer id;
    private String author;
    private String  genre;
    private String title;
    private String synopsis;
    private List<Integer> stockTypesIds;
}
