package com.files.library.service;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;

import java.util.List;

public interface StockTypeService {

    List<StockTypeDto> getStocksByBookId(Integer id);
    StockTypeDto createStock(StockTypeDto stockTypeDto);
    String deleteStockById(Integer id);
    String modifyStockById(Integer id, StockTypeDto stockTypeDto);
}
