package com.files.library.service;

import com.files.library.model.LeaseDto;
import com.files.library.model.LibraryUserDto;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.LibraryUser;
import com.files.library.repository.LibraryUserRepository;
import com.files.library.service.impl.LibraryUserServiceImpl;
import com.files.library.util.LibraryUserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class LibraryUserServiceTest {

    @Mock
    private LibraryUserRepository libraryUserRepository;

    @InjectMocks
    private LibraryUserServiceImpl libraryUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        // Arrange
        List<LibraryUser> users = List.of(
                new LibraryUser(),
                new LibraryUser()
        );

        // Mock para simular la respuesta del repositorio
        when(libraryUserRepository.findAll()).thenReturn(users);

        // Act
        List<LibraryUserDto> result = libraryUserService.getAllUsers();

        // Assert
        verify(libraryUserRepository, times(1)).findAll();
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals(users.size(), result.size());
        assertTrue(result.containsAll(users));
    }

    @Test
    void getUserById_UserExists_ReturnsUser() {
        // Arrange
        int id = 1;
        LibraryUser expectedUser = new LibraryUser();

        // Mock para simular que el usuario existe en la base de datos
        when(libraryUserRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        // Act
        LibraryUserDto result = libraryUserService.getUserById(id);

        // Assert
        verify(libraryUserRepository, times(1)).findById(id);
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals(expectedUser, result);
    }

    @Test
    void getUserById_UserDoesNotExist_ReturnsNull() {
        // Arrange
        int id = 1;

        // Mock para simular que el usuario no existe en la base de datos
        when(libraryUserRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        LibraryUserDto result = libraryUserService.getUserById(id);

        // Assert
        verify(libraryUserRepository, times(1)).findById(id);
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals(null, result);
    }

    @Test
    void createUser_UserCreated_ReturnsCreatedUser() {
        // Arrange
        LibraryUserDto newUser = new LibraryUserDto();
        LibraryUser savedUser = new LibraryUser();

        // Mock para simular que el usuario ha sido guardado correctamente en la base de datos
        when(libraryUserRepository.save(LibraryUserMapper.libraryUserMapperDtoToEntity(newUser))).thenReturn(savedUser);

        // Act
        LibraryUserDto result = libraryUserService.createUser(newUser);

        // Assert
        verify(libraryUserRepository, times(1)).save(LibraryUserMapper.libraryUserMapperDtoToEntity(newUser));
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals(savedUser, result);
    }

    @Test
    void deleteUserById_UserExists_SuccessfullyDeleted() {
        // Arrange
        int id = 1;

        // Mock para simular que el usuario existe en la base de datos
        when(libraryUserRepository.findById(id)).thenReturn(Optional.of(new LibraryUser()));

        // Act
        String result = libraryUserService.deleteUserById(id);

        // Assert
        verify(libraryUserRepository, times(1)).findById(id);
        verify(libraryUserRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals("User with id " + id + " successfully deleted.", result);
    }

    @Test
    void deleteUserById_UserDoesNotExist_ReturnsErrorMessage() {
        // Arrange
        int id = 1;

        // Mock para simular que el usuario no existe en la base de datos
        when(libraryUserRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        String result = libraryUserService.deleteUserById(id);

        // Assert
        verify(libraryUserRepository, times(1)).findById(id);
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals("The user with id " + id + "does not exists in our BD.", result);
    }

    @Test
    void modifyUserById_UserExists_SuccessfullyUpdated() {
        // Arrange
        int id = 1;
        LibraryUserDto libraryUser = new LibraryUserDto();
        libraryUser.setUserName("UpdatedUserName");
        libraryUser.setEmail("updated@example.com");
        libraryUser.setPassword("updatedPassword");
        libraryUser.setAddress("UpdatedAddress");

        List<Integer> newLeases = new ArrayList<>();
        newLeases.add(1);
        newLeases.add(1);
        libraryUser.setLeasedBooksIds(newLeases);

        LibraryUser existingUser = new LibraryUser();
        existingUser.setId(id);

        // Mock para simular que el usuario existente se encuentra en la base de datos
        when(libraryUserRepository.findById(id)).thenReturn(Optional.of(existingUser));

        // Act
        String result = libraryUserService.modifyUserById(id, libraryUser);

        // Assert
        verify(libraryUserRepository, times(1)).findById(id);
        verify(libraryUserRepository, times(1)).save(existingUser);
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals("User with id " + id + " successfully updated.", result);
    }

    @Test
    void modifyUserById_UserDoesNotExist_ReturnsErrorMessage() {
        // Arrange
        int id = 1;
        LibraryUserDto libraryUser = new LibraryUserDto();
        libraryUser.setUserName("UpdatedUserName");
        libraryUser.setEmail("updated@example.com");
        libraryUser.setPassword("updatedPassword");
        libraryUser.setAddress("UpdatedAddress");

        // Mock para simular que el usuario no existe en la base de datos
        when(libraryUserRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        String result = libraryUserService.modifyUserById(id, libraryUser);

        // Assert
        verify(libraryUserRepository, times(1)).findById(id);
        verifyNoMoreInteractions(libraryUserRepository);

        assertEquals("User with id " + id + " does not exists in our DB. Please, type a existent id.", result);
    }
}