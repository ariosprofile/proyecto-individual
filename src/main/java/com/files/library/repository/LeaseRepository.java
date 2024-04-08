package com.files.library.repository;

import com.files.library.model.domain.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasesRepository extends JpaRepository<Lease, Integer> {
}
