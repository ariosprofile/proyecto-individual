package com.files.library.repository;

import com.files.library.model.domain.StockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<StockType, Integer> {
}
