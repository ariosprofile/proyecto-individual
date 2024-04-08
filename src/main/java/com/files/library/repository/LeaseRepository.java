package com.files.library.repository;

import com.files.library.model.domain.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Integer> {

    List<Lease> findByUserId(Integer id);

    List<Lease> findByStockTypeId(Integer id);

}
