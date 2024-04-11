package com.files.library.service.impl;

import com.files.library.model.domain.Lease;
import com.files.library.model.domain.LibraryUser;
import com.files.library.repository.LibraryUserRepository;
import com.files.library.service.LibraryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryUserServiceImpl implements LibraryUserService {

    private final LibraryUserRepository libraryUserRepository;

    @Override
    public List<LibraryUser> getAllUsers() {
        return libraryUserRepository.findAll();
    }

    @Override
    public LibraryUser getUserById(Integer id) {
        Optional<LibraryUser> user = libraryUserRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public LibraryUser createUser(LibraryUser user) {
        return libraryUserRepository.save(user);
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
    public String modifyUserById(Integer id, LibraryUser libraryUser) {
        Optional<LibraryUser> user = libraryUserRepository.findById(id);

        if (user.isEmpty()){
            return "User with id " + id + " does not exists in our DB. Please, type a existent id.";
        } else {
            LibraryUser existingUser = user.get();
            existingUser.setUserName(libraryUser.getUserName());
            existingUser.setAddress(libraryUser.getAddress());
            existingUser.setEmail(libraryUser.getEmail());
            existingUser.setPassword(libraryUser.getPassword());
            existingUser.setLeases(libraryUser.getLeases());
            libraryUserRepository.save(existingUser);
            return "User with id " + id + " successfully updated.";
        }
    }
}
