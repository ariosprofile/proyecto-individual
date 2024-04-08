package com.files.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StockTypeDto {

    private Integer stock;
    private Integer type;
    private Double costPerDay;
    private List<LeasesDto> leasedCopies;
}
