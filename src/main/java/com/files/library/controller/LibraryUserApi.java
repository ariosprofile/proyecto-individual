package com.files.library.controller;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/libraryUser")
public interface LibraryUserApi {
    @GetMapping
    List<LibraryUserDto> getAllUsers();

    @GetMapping("/{id}")
    LibraryUserDto getLibraryUserById(Integer id);

    @PostMapping
    LibraryUser createNewLibraryUser(@RequestBody LibraryUserDto libraryUserDto);

    @DeleteMapping
    String deleteUserById(Integer id);

    @PutMapping
    String modifyUserById(Integer id, @RequestBody LibraryUserDto userDto);
}
