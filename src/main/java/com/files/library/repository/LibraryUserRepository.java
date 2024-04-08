package com.files.library.repository;

import com.files.library.model.domain.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, Integer> {
}