package com.files.library.service.impl;

import com.files.library.model.LeaseDto;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Book;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.LibraryUser;
import com.files.library.model.domain.StockType;
import com.files.library.repository.LeaseRepository;
import com.files.library.repository.LibraryUserRepository;
import com.files.library.repository.StockTypeRepository;
import com.files.library.service.LeaseService;
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
public class LeaseServiceImpl implements LeaseService {

    private final LeaseRepository leaseRepository;
    private final LibraryUserRepository libraryUserRepository;
    private final StockTypeRepository stockTypeRepository;

    @Override
    public List<LeaseDto> getLeasesByUserId(Integer id) {

        List<LeaseDto> leaseDtos = new ArrayList<>();

        for (Lease entityLease: leaseRepository.findByUserId(id)) {
            leaseDtos.add(LeaseMapper.leaseMapperEntityToDto(entityLease));
        }

        return leaseDtos;
    }

    @Override
    public List<LeaseDto> getLeasesByStockId(Integer id) {

        List<LeaseDto> leaseDtos = new ArrayList<>();

        for (Lease entityLease: leaseRepository.findByStockTypeId(id)) {
            leaseDtos.add(LeaseMapper.leaseMapperEntityToDto(entityLease));
        }

        return leaseDtos;
    }

    @Override
    public LeaseDto createNewLease(LeaseDto leaseDto) {

        Optional<LibraryUser> libraryUser = libraryUserRepository.findById(leaseDto.getLibraryUserId());
        Optional<StockType> stockType = stockTypeRepository.findById(leaseDto.getStockTypeId());

        if (libraryUser.isPresent() && stockType.isPresent()){
            Lease lease = leaseRepository.save(LeaseMapper.leaseMapperDtoToEntity(leaseDto));
            lease.setUser(libraryUser.get());
            lease.setStockType(stockType.get());
            return LeaseMapper.leaseMapperEntityToDto(leaseRepository.save(lease));
        } else {
            throw new EntityNotFoundException("Library User or stock type with ids: "
                    + leaseDto.getLibraryUserId()
                    + ", " + leaseDto.getStockTypeId() +
                    ", respectively, not found to associate.");
        }
    }

    @Override
    public String deleteLeaseById(Integer id) {
        Optional<Lease> lease = leaseRepository.findById(id);

        if (lease.isEmpty()){
            return "Couldn't find the lease with id: " + id;
        } else {
            leaseRepository.deleteById(id);
            return "Lease successfully deleted.";
        }
    }
    @Override
    public String modifyLeaseById(Integer id, LeaseDto modifiedLeaseDto) {
        Optional<Lease> lease = leaseRepository.findById(id);

        if (lease.isEmpty()){
            return "Lease with id " + id + " does not exists in our DB. Please, type a existent id.";
        } else {
            Optional<LibraryUser> libraryUser = libraryUserRepository.findById(modifiedLeaseDto.getLibraryUserId());
            Optional<StockType> stockType = stockTypeRepository.findById(modifiedLeaseDto.getStockTypeId());
            if (libraryUser.isPresent() && stockType.isPresent()){
                Lease existingLease = lease.get();
                existingLease.setLeaseDate(modifiedLeaseDto.getLeaseDate());
                existingLease.setUser(libraryUser.get());
                existingLease.setTotalCost(modifiedLeaseDto.getTotalCost());
                existingLease.setReturnDate(modifiedLeaseDto.getReturnDate());
                existingLease.setStockType(stockType.get());
                leaseRepository.save(existingLease);
                return "Lease with id " + id + " successfully updated.";
            } else {
                return "Library user or stock type not found with ids: "
                        + modifiedLeaseDto.getLibraryUserId()
                        + modifiedLeaseDto.getStockTypeId()
                        + " , respectively, to associate.";
            }
        }
    }
}
