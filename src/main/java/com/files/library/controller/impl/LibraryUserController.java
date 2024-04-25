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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Existing Users", Integer.toString(libraryUsersDto.size()));
        return new ResponseEntity<>(libraryUsersDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<LibraryUserDto> getLibraryUserById(Integer id) {
        LibraryUserDto libraryUser = libraryUserService.getUserById(id);
        return new ResponseEntity<>(libraryUser, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<LibraryUserDto> createNewLibraryUser(LibraryUserDto libraryUserDto) {
        LibraryUserDto newLibraryUser = libraryUserService.createUser(libraryUserDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Created id", newLibraryUser.getId().toString());
        return new ResponseEntity<>(newLibraryUser, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Integer id) {
        String deleteMessage = libraryUserService.deleteUserById(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> modifyUserById(Integer id, LibraryUserDto userDto) {
        String modifyMessage = libraryUserService.modifyUserById(id, userDto);
        return new ResponseEntity<>(modifyMessage, HttpStatus.ACCEPTED);
    }
}
