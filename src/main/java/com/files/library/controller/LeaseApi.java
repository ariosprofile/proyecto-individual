package com.files.library.controller;

import com.files.library.model.LeaseDto;
import com.files.library.model.domain.Lease;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/lease")
public interface LeaseApi {

    @GetMapping("/userId/{id}")
    ResponseEntity<List<LeaseDto>> getLeasesByUserId(@PathVariable Integer id);

    @GetMapping("/stockId/{id}")
    ResponseEntity<List<LeaseDto>> getLeasesByStockId(@PathVariable Integer id);

    @PostMapping
    ResponseEntity<LeaseDto> createNewLease(@RequestBody LeaseDto leaseDto);

    @DeleteMapping
    ResponseEntity<Void> deleteLeaseById(Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> modifyLeaseById(@PathVariable Integer id, @RequestBody LeaseDto leaseDto);
}
