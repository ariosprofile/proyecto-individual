package com.files.library.repository;

import com.files.library.model.domain.StockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTypeRepository extends JpaRepository<StockType, Integer> {

    List<StockType> findByBookId(Integer id);
}
