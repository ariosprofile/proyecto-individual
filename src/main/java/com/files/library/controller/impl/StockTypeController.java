package com.files.library.controller.impl;

import com.files.library.controller.StockTypeApi;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;
import com.files.library.service.StockTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockTypeController implements StockTypeApi {

    private final StockTypeService stockTypeService;
    @Override
    public ResponseEntity<List<StockTypeDto>>  getStockTypesByBookId(Integer id) {
        List<StockTypeDto> stockTypes = stockTypeService.getStocksByBookId(id);
        return new ResponseEntity<>(stockTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StockTypeDto> createNewStockType(StockTypeDto stockTypeDto) {
        StockTypeDto newStockType = stockTypeService.createStock(stockTypeDto);
        return new ResponseEntity<>(newStockType, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteStockTypeById(Integer id) {
        stockTypeService.deleteStockById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> modifyExistingStockById(Integer id, StockTypeDto stockTypeDto) {
        stockTypeService.modifyStockById(id, stockTypeDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
