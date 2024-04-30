package com.files.library.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUserDto {

    private Integer id;
    private String address;
    private String email;
    private String password;
    private String userName;
    private List<Integer> leasedBooksIds;
}
