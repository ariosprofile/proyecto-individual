package com.files.library.controller;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/stockType")
public interface StockTypeApi {

    @GetMapping
    List<StockTypeDto> getStockTypesByBookId(Integer id);
    @PostMapping
    StockType createNewStockType(@RequestBody StockTypeDto stockTypeDto);
    @DeleteMapping
    String deleteStockTypeById(Integer id);
    @PutMapping
    String modifyExistingStockById(Integer id, @RequestBody StockTypeDto stockTypeDto);



}
