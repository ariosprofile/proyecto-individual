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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaseServiceImpl implements LeaseService {

    private final LeaseRepository leaseRepository;
    private final LibraryUserRepository libraryUserRepository;
    private final StockTypeRepository stockTypeRepository;

    @Override
    public List<LeaseDto> getLeasesByUserId(Integer id) {

        List<Lease> leases = leaseRepository.findByUserId(id);

        if (leases.isEmpty()){
            throw new EntityNotFoundException("No leases to show in our DB.");
        }

        return  leases
                .stream()
                .map(LeaseMapper :: leaseMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaseDto> getLeasesByStockId(Integer id) {

        List<Lease> leases = leaseRepository.findByStockTypeId(id);

        if (leases.isEmpty()){
            throw new EntityNotFoundException("No leases to show in our DB.");
        }

        return  leases
                .stream()
                .map(LeaseMapper :: leaseMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LeaseDto createNewLease(LeaseDto leaseDto) {

        if (leaseDto == null) {
            throw new IllegalArgumentException("LeaseDto object cannot be null.");
        }

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
    public void deleteLeaseById(Integer id) {
        Optional<Lease> lease = leaseRepository.findById(id);
        if (lease.isEmpty()){
            throw new EntityNotFoundException("Couldn't find the lease with id: " + id);
        } else {
            try {
                leaseRepository.deleteById(id);
            } catch (InternalError e){
                e.addSuppressed(new Throwable("A problem occurred in the process. Try again in a few minutes."));
            }

        }
    }
    @Override
    public void modifyLeaseById(Integer id, LeaseDto modifiedLeaseDto) {
        Optional<Lease> lease = leaseRepository.findById(id);

        if (lease.isEmpty()){
            throw new EntityNotFoundException("Lease with id " + id + " does not exists in our DB. Please, type a existent id.");
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

                try {
                    leaseRepository.save(existingLease);
                } catch (InternalError e){
                    e.addSuppressed(new Throwable("A problem occurred in the process. Try again in a few minutes."));
                }

            } else {
                throw new EntityNotFoundException(
                        "Library user or stock type not found with ids "
                        + modifiedLeaseDto.getLibraryUserId()
                        + ", "
                        + modifiedLeaseDto.getStockTypeId()
                        + " , respectively, to associate.");
            }
        }
    }
}
