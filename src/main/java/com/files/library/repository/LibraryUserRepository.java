package com.files.library.repository;

import com.files.library.model.domain.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {

    List<LibraryUser> findByUserNameContaining(String userName);

}
