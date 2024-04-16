package com.files.library.controller.impl;

import com.files.library.controller.StockTypeApi;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;
import com.files.library.service.StockTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockTypeController implements StockTypeApi {

    private final StockTypeService stockTypeService;
    @Override
    public List<StockTypeDto> getStockTypesByBookId(Integer id) {
        return stockTypeService.getStocksByBookId(id);
    }

    @Override
    public StockType createNewStockType(StockTypeDto stockTypeDto) {
        return stockTypeService.createStock(stockTypeDto);
    }

    @Override
    public String deleteStockTypeById(Integer id) {
        return stockTypeService.deleteStockById(id);
    }

    @Override
    public String modifyExistingStockById(Integer id, StockTypeDto stockTypeDto) {
        return stockTypeService.modifyStockById(id, stockTypeDto);
    }
}
