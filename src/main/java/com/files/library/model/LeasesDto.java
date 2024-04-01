package com.files.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LeasesDto {

    private LocalDate lease_date;

    private LocalDate return_date;

}
