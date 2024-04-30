package com.files.library.controller.impl;

import com.files.library.controller.LeaseApi;
import com.files.library.model.LeaseDto;
import com.files.library.model.domain.Lease;
import com.files.library.service.LeaseService;
import com.sun.net.httpserver.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LeaseController implements LeaseApi {

    private final LeaseService leaseService;
    @Override
    public ResponseEntity<List<LeaseDto>> getLeasesByUserId(Integer id) {
        List<LeaseDto> leasesDto = leaseService.getLeasesByUserId(id);
        return new ResponseEntity<>(leasesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LeaseDto>> getLeasesByStockId(Integer id) {
        List<LeaseDto> leasesDto = leaseService.getLeasesByStockId(id);
        return new ResponseEntity<>(leasesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LeaseDto> createNewLease(LeaseDto leaseDto) {
        LeaseDto newLease = leaseService.createNewLease(leaseDto);
        return new ResponseEntity<>(newLease, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteLeaseById(Integer id) {
        leaseService.deleteLeaseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> modifyLeaseById(Integer id, LeaseDto leaseDto) {
        leaseService.modifyLeaseById(id, leaseDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
