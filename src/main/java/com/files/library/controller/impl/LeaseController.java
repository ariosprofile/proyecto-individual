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
        HttpHeaders headers = new HttpHeaders();
        headers.add("User existing leases", Integer.toString(leasesDto.size()));
        return new ResponseEntity<>(leasesDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<LeaseDto>> getLeasesByStockId(Integer id) {
        List<LeaseDto> leasesDto = leaseService.getLeasesByStockId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Existing leases of this kind of stock", Integer.toString(leasesDto.size()));
        return new ResponseEntity<>(leasesDto, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<LeaseDto> createNewLease(LeaseDto leaseDto) {
        LeaseDto newLease = leaseService.createNewLease(leaseDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Created id", newLease.getId().toString());
        return new ResponseEntity<>(newLease, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteLeaseById(Integer id) {
        String deleteMessage = leaseService.deleteLeaseById(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> modifyLeaseById(Integer id, LeaseDto leaseDto) {
        String modifyMessage = leaseService.modifyLeaseById(id, leaseDto);
        return new ResponseEntity<>(modifyMessage, HttpStatus.ACCEPTED);
    }
}
