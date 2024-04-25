package com.files.library.controller;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/libraryUser")
public interface LibraryUserApi {
    @GetMapping
    ResponseEntity<List<LibraryUserDto>> getAllUsers();

    @GetMapping("/{id}")
    ResponseEntity<LibraryUserDto> getLibraryUserById(Integer id);

    @PostMapping
    ResponseEntity<LibraryUserDto> createNewLibraryUser(@RequestBody LibraryUserDto libraryUserDto);

    @DeleteMapping
    ResponseEntity<String> deleteUserById(Integer id);

    @PutMapping("/{id}")
    ResponseEntity<String> modifyUserById(@PathVariable Integer id, @RequestBody LibraryUserDto userDto);
}
