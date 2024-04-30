package com.files.library.util;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;

public class LibraryUserMapper {

    public static LibraryUserDto libraryUserMapperEntityToDto(LibraryUser libraryUser){

        if (libraryUser == null){
            throw new IllegalArgumentException("LibraryUser object cannot be null");
        }

        return LibraryUserDto.builder()
                .id(libraryUser.getId())
                .address(libraryUser.getAddress())
                .userName(libraryUser.getUserName())
                .email(libraryUser.getEmail())
                .password(libraryUser.getPassword())
                .leasedBooksIds(LeaseMapper.mapLeasesFromEntityToDto(libraryUser.getLeases()))
                .build();
    }

    public static LibraryUser libraryUserMapperDtoToEntity(LibraryUserDto libraryUserDto){

        if (libraryUserDto == null){
            throw new IllegalArgumentException("LibraryUser object cannot be null");
        }

        return LibraryUser.builder()
                .address(libraryUserDto.getAddress())
                .userName(libraryUserDto.getUserName())
                .email(libraryUserDto.getEmail())
                .password(libraryUserDto.getPassword())
                .build();
    }
}
