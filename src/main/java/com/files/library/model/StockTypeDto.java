package com.files.library.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockTypeDto {

    private Integer stock;
    private Integer type;
    private Double costPerDay;
    private BookDto bookDto;
    private List<LeaseDto> leases;
}
