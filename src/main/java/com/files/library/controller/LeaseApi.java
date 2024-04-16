package com.files.library.controller;

import com.files.library.model.LeaseDto;
import com.files.library.model.domain.Lease;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/lease")
public interface LeaseApi {

    @GetMapping("/userId/{id}")
    List<LeaseDto> getLeasesByUserId(@PathVariable Integer id);

    @GetMapping("/stockId/{id}")
    List<LeaseDto> getLeasesByStockId(@PathVariable Integer id);

    @PostMapping
    Lease createNewLease(@RequestBody LeaseDto leaseDto);

    @DeleteMapping
    String deleteLeaseById(Integer id);

    @PutMapping
    String modifyLeaseById(Integer id, @RequestBody LeaseDto leaseDto);
}
