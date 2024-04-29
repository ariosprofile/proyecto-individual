package com.files.library.controller;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/stockType")
public interface StockTypeApi {

    @GetMapping("/{id}")
    ResponseEntity<List<StockTypeDto>> getStockTypesByBookId(@PathVariable Integer id);
    @PostMapping
    ResponseEntity<StockTypeDto>  createNewStockType(@RequestBody StockTypeDto stockTypeDto);
    @DeleteMapping
    ResponseEntity<Void>  deleteStockTypeById(Integer id);
    @PutMapping("/{id}")
    ResponseEntity<Void>  modifyExistingStockById(@PathVariable Integer id, @RequestBody StockTypeDto stockTypeDto);



}
