package com.files.library.service.impl;

import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.StockType;
import com.files.library.repository.StockTypeRepository;
import com.files.library.service.StockTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockTypeServiceImpl implements StockTypeService {

    private final StockTypeRepository stockTypeRepository;

    @Override
    public List<StockType> getStocksByBookId(Integer id) {
        return stockTypeRepository.findByBookId(id);
    }

    @Override
    public StockType createStock(StockType stockType) {
        return stockTypeRepository.save(stockType);
    }

    @Override
    public String deleteStockById(Integer id) {

        Optional<StockType> stock = stockTypeRepository.findById(id);

        if (stock.isEmpty()){
            return "Stock with id " + id + "does not exists in our DB.";
        } else {
            return "Stock successfully deleted";
        }
    }

    @Override
    public String modifyStockById(Integer id, StockType stockType) {

        Optional<StockType> stock = stockTypeRepository.findById(id);

        if (stock.isEmpty()){
            return "Stock with id " + id + " does not exists in our DB. Please, type a existent id.";
        } else {
            stockTypeRepository.save(
                    StockType.builder()
                    .type(stockType.getType())
                            .stock(stockType.getStock())
                            .costPerDay(stockType.getCostPerDay())
                            .build());
            return "Stock with id " + id + " successfully updated.";
        }
    }
}
