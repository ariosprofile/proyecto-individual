package com.files.library.service;

import com.files.library.model.domain.StockType;

import java.util.List;

public interface StockTypeService {

    List<StockType> getStocksByBookId(Integer id);
    StockType createStock(StockType stockType);
    String deleteStockById(Integer id);
    String modifyStockById(Integer id, StockType stockType);
}
