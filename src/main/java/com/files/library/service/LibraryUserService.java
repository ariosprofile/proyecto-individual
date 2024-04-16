package com.files.library.service;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;

import java.util.List;

public interface LibraryUserService {

    List<LibraryUserDto> getAllUsers();
    LibraryUserDto getUserById(Integer id);
    LibraryUser createUser(LibraryUserDto userDto);
    String deleteUserById(Integer id);
    String modifyUserById(Integer id, LibraryUserDto libraryUserDto);

}
