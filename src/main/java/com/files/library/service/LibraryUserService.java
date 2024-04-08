package com.files.library.service;

import com.files.library.model.domain.LibraryUser;

import java.util.List;

public interface LibraryUserService {

    List<LibraryUser> getAllUsers();
    LibraryUser getUserById(Integer id);
    LibraryUser createUser(LibraryUser user);
    String deleteUserById(Integer id);
    String modifyUserById(Integer id, LibraryUser libraryUser);

}
