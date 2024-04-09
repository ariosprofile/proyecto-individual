package com.files.library.service.impl;

import com.files.library.model.domain.Book;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.StockType;
import com.files.library.repository.LeaseRepository;
import com.files.library.service.LeaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaseServiceImpl implements LeaseService {

    private final LeaseRepository leaseRepository;
    @Override
    public List<Lease> getLeasesByUserId(Integer id) {
        return leaseRepository.findByUserId(id);
    }

    @Override
    public List<Lease> getLeasesByStockId(Integer id) {
        return leaseRepository.findByStockTypeId(id);
    }

    @Override
    public String deleteLeaseById(Integer id) {
        Optional<Lease> lease = leaseRepository.findById(id);

        if (lease.isEmpty()){
            return "Couldn't find the lease with id: " + id;
        } else {
            leaseRepository.delete(lease.get());
            return "Lease successfully deleted.";
        }
    }
    @Override
    public String modifyLeaseById(Integer id, Lease modifiedLease) {
        Optional<Lease> lease = leaseRepository.findById(id);

        if (lease.isEmpty()){
            return "Lease with id " + id + " does not exists in our DB. Please, type a existent id.";
        } else {
            leaseRepository.save(
                    Lease.builder()
                            .leaseDate(modifiedLease.getLeaseDate())
                            .returnDate(modifiedLease.getReturnDate())
                            .totalCost(modifiedLease.getTotalCost())
                            .build());
            return "Lease with id " + id + " successfully updated.";
        }
    }
}
