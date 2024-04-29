package com.files.library.service;

import com.files.library.model.LeaseDto;
import com.files.library.model.domain.Lease;

import java.util.List;

public interface LeaseService {

    List<LeaseDto> getLeasesByUserId(Integer id);
    List<LeaseDto> getLeasesByStockId(Integer id);
    LeaseDto createNewLease(LeaseDto leaseDto);
    void deleteLeaseById(Integer id);
    void modifyLeaseById(Integer id, LeaseDto modifiedLeaseDto);
}
