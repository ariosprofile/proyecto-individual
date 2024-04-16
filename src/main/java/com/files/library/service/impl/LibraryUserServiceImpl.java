package com.files.library.service.impl;

import com.files.library.model.BookDto;
import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.LibraryUser;
import com.files.library.repository.LibraryUserRepository;
import com.files.library.service.LibraryUserService;
import com.files.library.util.BookMapper;
import com.files.library.util.LeaseMapper;
import com.files.library.util.LibraryUserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryUserServiceImpl implements LibraryUserService {

    private final LibraryUserRepository libraryUserRepository;

    @Override
    public List<LibraryUserDto> getAllUsers() {

        List<LibraryUserDto> libraryUserDtos = new ArrayList<>();

        for (LibraryUser entityLibraryUser: libraryUserRepository.findAll()) {
            libraryUserDtos.add(LibraryUserMapper.libraryUserMapperEntityToDto(entityLibraryUser));
        }
        return libraryUserDtos;
    }

    @Override
    public LibraryUserDto getUserById(Integer id) {
        Optional<LibraryUser> user = libraryUserRepository.findById(id);

        if (user.isPresent()){
            return LibraryUserMapper.libraryUserMapperEntityToDto(user.get());
        } else {
            throw new EntityNotFoundException("User with id " + id + " does not exists in our DB.");
        }
    }

    @Override
    public LibraryUser createUser(LibraryUserDto userDto) {
        return libraryUserRepository.save(LibraryUserMapper.libraryUserMapperDtoToEntity(userDto));
    }

    @Override
    public String deleteUserById(Integer id) {
        Optional<LibraryUser> user = libraryUserRepository.findById(id);

        if (user.isEmpty()){
            return "The user with id " + id + "does not exists in our BD.";
        } else {
            libraryUserRepository.deleteById(id);
            return "User with id " + id + " successfully deleted.";
        }
    }

    @Override
    public String modifyUserById(Integer id, LibraryUserDto libraryUserDto) {
        Optional<LibraryUser> user = libraryUserRepository.findById(id);

        if (user.isEmpty()){
            return "User with id " + id + " does not exists in our DB. Please, type a existent id.";
        } else {
            LibraryUser existingUser = user.get();
            existingUser.setUserName(libraryUserDto.getUserName());
            existingUser.setAddress(libraryUserDto.getAddress());
            existingUser.setEmail(libraryUserDto.getEmail());
            existingUser.setPassword(libraryUserDto.getPassword());
            existingUser.setLeases(LeaseMapper.mapLeasesFromDtoToEntity(libraryUserDto.getLeasedBooks()));
            libraryUserRepository.save(existingUser);
            return "User with id " + id + " successfully updated.";
        }
    }
}
