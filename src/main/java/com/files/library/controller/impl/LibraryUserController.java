package com.files.library.controller.impl;

import com.files.library.controller.LibraryUserApi;
import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.LibraryUser;
import com.files.library.service.LibraryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LibraryUserController implements LibraryUserApi {

    private final LibraryUserService libraryUserService;
    @Override
    public ResponseEntity<List<LibraryUserDto>> getAllUsers() {
        List<LibraryUserDto> libraryUsersDto = libraryUserService.getAllUsers();
        return new ResponseEntity<>(libraryUsersDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibraryUserDto> getLibraryUserById(Integer id) {
        LibraryUserDto libraryUser = libraryUserService.getUserById(id);
        return new ResponseEntity<>(libraryUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibraryUserDto> createNewLibraryUser(LibraryUserDto libraryUserDto) {
        LibraryUserDto newLibraryUser = libraryUserService.createUser(libraryUserDto);
        return new ResponseEntity<>(newLibraryUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Integer id) {
        libraryUserService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> modifyUserById(Integer id, LibraryUserDto userDto) {
        libraryUserService.modifyUserById(id, userDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
