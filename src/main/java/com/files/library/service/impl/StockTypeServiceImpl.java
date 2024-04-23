package com.files.library.service.impl;

import com.files.library.model.LibraryUserDto;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.LibraryUser;
import com.files.library.model.domain.StockType;
import com.files.library.repository.BookRepository;
import com.files.library.repository.LeaseRepository;
import com.files.library.repository.StockTypeRepository;
import com.files.library.service.StockTypeService;
import com.files.library.util.BookMapper;
import com.files.library.util.LeaseMapper;
import com.files.library.util.LibraryUserMapper;
import com.files.library.util.StockTypeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockTypeServiceImpl implements StockTypeService {

    private final StockTypeRepository stockTypeRepository;
    private final BookRepository bookRepository;
    private final LeaseRepository leaseRepository;

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
        Optional<Book> book = bookRepository.findById(stockTypeDto.getBookId());

        if (book.isPresent()){
            StockType stockType = StockTypeMapper.stockTypeMapperDtoToEntity(stockTypeDto);
            stockType.setBook(book.get());
            return stockTypeRepository.save(stockType);
        } else {
            throw new EntityNotFoundException("Book not found to associate with id: " + stockTypeDto.getBookId());
        }
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
            Optional<Book> book = bookRepository.findById(stockTypeDto.getBookId());
            if (book.isPresent()) {

                StockType existingStock = stock.get();
                existingStock.setStock(stockTypeDto.getStock());
                existingStock.setType(stockTypeDto.getType());
                existingStock.setCostPerDay(stockTypeDto.getCostPerDay());

                List<Lease> leases = new ArrayList<>();

                for (Integer leaseId : stockTypeDto.getLeasesIds()) {
                    Optional<Lease> lease = leaseRepository.findById(leaseId);

                    leases.add(lease.orElseThrow());
                }

                existingStock.setLeases(leases);
                existingStock.setBook(book.get());
                stockTypeRepository.save(existingStock);
                return "Stock with id " + id + " successfully updated.";
            } else {
                return "Book not found with id: " + stockTypeDto.getBookId() + ", to associate.";
            }
        }
    }
}
