package com.files.library.service;

import com.files.library.model.domain.Lease;

import java.util.List;

public interface LeaseService {

    List<Lease> getLeasesByUserId(Integer id);
    List<Lease> getLeasesByStockId(Integer id);
    String deleteLeaseById(Integer id);
    String modifyLeaseById(Integer id, Lease modifiedLease);
}
