package com.files.library.service.impl;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.LibraryUser;
import com.files.library.model.domain.StockType;
import com.files.library.repository.StockTypeRepository;
import com.files.library.service.StockTypeService;
import com.files.library.util.BookMapper;
import com.files.library.util.LeaseMapper;
import com.files.library.util.LibraryUserMapper;
import com.files.library.util.StockTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockTypeServiceImpl implements StockTypeService {

    private final StockTypeRepository stockTypeRepository;

    @Override
    public List<StockTypeDto> getStocksByBookId(Integer id) {

        List<StockTypeDto> stockTypeDtos = new ArrayList<>();

        for (StockType entityStockType: stockTypeRepository.findByBookId(id)) {
            stockTypeDtos.add(StockTypeMapper.stockTypeMapperEntityToDto(entityStockType));
        }

        return stockTypeDtos;
    }

    @Override
    public StockType createStock(StockTypeDto stockTypeDto) {
        return stockTypeRepository.save(StockTypeMapper.stockTypeMapperDtoToEntity(stockTypeDto));
    }

    @Override
    public String deleteStockById(Integer id) {

        Optional<StockType> stock = stockTypeRepository.findById(id);

        if (stock.isEmpty()){
            return "Stock with id " + id + "does not exists in our DB.";
        } else {
            stockTypeRepository.deleteById(id);
            return "Stock successfully deleted";
        }
    }

    @Override
    public String modifyStockById(Integer id, StockTypeDto stockTypeDto) {

        Optional<StockType> stock = stockTypeRepository.findById(id);

        if (stock.isEmpty()){
            return "Stock with id " + id + " does not exists in our DB. Please, type a existent id.";
        } else {
            StockType existingStock = stock.get();
            existingStock.setStock(stockTypeDto.getStock());
            existingStock.setType(stockTypeDto.getType());
            existingStock.setCostPerDay(stockTypeDto.getCostPerDay());
            existingStock.setLeases(LeaseMapper.mapLeasesFromDtoToEntity(stockTypeDto.getLeases()));
            existingStock.setBook(BookMapper.BookMapperDtoToEntity(stockTypeDto.getBookDto()));
            stockTypeRepository.save(existingStock);
            return "Stock with id " + id + " successfully updated.";
        }
    }
}
