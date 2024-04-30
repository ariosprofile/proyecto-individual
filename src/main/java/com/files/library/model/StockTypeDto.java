package com.files.library.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockTypeDto {

    private Integer id;
    private Integer stock;
    private Integer type;
    private Double costPerDay;
    private Integer bookId;
    private List<Integer> leasesIds;
}
