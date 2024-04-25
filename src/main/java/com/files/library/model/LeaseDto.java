package com.files.library.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaseDto {

    private Integer id;
    private LocalDate leaseDate;
    private LocalDate returnDate;
    private Double totalCost;
    private Integer libraryUserId;
    private Integer stockTypeId;

}
