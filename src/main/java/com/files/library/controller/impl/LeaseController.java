package com.files.library.controller.impl;

import com.files.library.controller.LeaseApi;
import com.files.library.model.LeaseDto;
import com.files.library.model.domain.Lease;
import com.files.library.service.LeaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LeaseController implements LeaseApi {

    private final LeaseService leaseService;
    @Override
    public List<LeaseDto> getLeasesByUserId(Integer id) {
        return leaseService.getLeasesByUserId(id);
    }

    @Override
    public List<LeaseDto> getLeasesByStockId(Integer id) {
        return leaseService.getLeasesByStockId(id);
    }

    @Override
    public LeaseDto createNewLease(LeaseDto leaseDto) {
        return leaseService.createNewLease(leaseDto);
    }

    @Override
    public String deleteLeaseById(Integer id) {
        return leaseService.deleteLeaseById(id);
    }

    @Override
    public String modifyLeaseById(Integer id, LeaseDto leaseDto) {
        return  leaseService.modifyLeaseById(id, leaseDto);
    }
}
