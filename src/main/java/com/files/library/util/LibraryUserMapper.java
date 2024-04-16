package com.files.library.util;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;

public class LibraryUserMapper {

    public static LibraryUserDto libraryUserMapperEntityToDto(LibraryUser libraryUser){

        return LibraryUserDto.builder()
                .address(libraryUser.getAddress())
                .userName(libraryUser.getUserName())
                .email(libraryUser.getUserName())
                .leasedBooks(LeaseMapper.mapLeasesFromEntityToDto(libraryUser.getLeases()))
                .build();
    }

    public static LibraryUser libraryUserMapperDtoToEntity(LibraryUserDto libraryUserDto){
        return LibraryUser.builder()
                .address(libraryUserDto.getAddress())
                .userName(libraryUserDto.getUserName())
                .email(libraryUserDto.getEmail())
                .leases(LeaseMapper.mapLeasesFromDtoToEntity(libraryUserDto.getLeasedBooks()))
                .build();
    }
}
