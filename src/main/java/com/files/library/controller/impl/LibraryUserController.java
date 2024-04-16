package com.files.library.controller.impl;

import com.files.library.controller.LibraryUserApi;
import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;
import com.files.library.service.LibraryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryUserController implements LibraryUserApi {

    private final LibraryUserService libraryUserService;
    @Override
    public List<LibraryUserDto> getAllUsers() {
        return libraryUserService.getAllUsers();
    }

    @Override
    public LibraryUserDto getLibraryUserById(Integer id) {
        return libraryUserService.getUserById(id);
    }

    @Override
    public LibraryUser createNewLibraryUser(LibraryUserDto libraryUserDto) {
        return libraryUserService.createUser(libraryUserDto);
    }

    @Override
    public String deleteUserById(Integer id) {
        return libraryUserService.deleteUserById(id);
    }

    @Override
    public String modifyUserById(Integer id, LibraryUserDto userDto) {
        return libraryUserService.modifyUserById(id, userDto);
    }
}
