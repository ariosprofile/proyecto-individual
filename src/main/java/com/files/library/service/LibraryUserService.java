package com.files.library.service;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;

import java.util.List;

public interface LibraryUserService {

    List<LibraryUserDto> getAllUsers();
    LibraryUserDto getUserById(Integer id);
    LibraryUserDto createUser(LibraryUserDto userDto);
    void deleteUserById(Integer id);
    void modifyUserById(Integer id, LibraryUserDto libraryUserDto);

}
