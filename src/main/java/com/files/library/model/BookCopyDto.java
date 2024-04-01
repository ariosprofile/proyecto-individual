package com.files.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BookCopyDto {

    private Integer copies_available;

    private List<LeasesDto> leasedCopies;
}
