package com.files.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LibraryUserDto {

    private String address;

    private String email;

    private String password;

    private String user_name;

    private List<LeasesDto> leasedBooks;
}
