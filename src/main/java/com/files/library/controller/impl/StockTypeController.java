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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Existing stocks of book with id " + id, Integer.toString(stockTypes.size()));
        return new ResponseEntity<>(stockTypes, headers, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<StockTypeDto> createNewStockType(StockTypeDto stockTypeDto) {
        StockTypeDto newStockType = stockTypeService.createStock(stockTypeDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Created id", newStockType.getId().toString());
        return new ResponseEntity<>(newStockType, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteStockTypeById(Integer id) {
        String deleteMessage = stockTypeService.deleteStockById(id);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> modifyExistingStockById(Integer id, StockTypeDto stockTypeDto) {
        String modifyMessage = stockTypeService.modifyStockById(id, stockTypeDto);
        return new ResponseEntity<>(modifyMessage, HttpStatus.ACCEPTED);
    }
}
