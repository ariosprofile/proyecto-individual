package com.files.library.util;

import com.files.library.model.LeaseDto;
import com.files.library.model.LibraryUserDto;
import com.files.library.model.StockTypeDto;
import com.files.library.model.domain.Lease;
import com.files.library.model.domain.LibraryUser;
import com.files.library.model.domain.StockType;

import java.util.ArrayList;
import java.util.List;

public class LeaseMapper {

    public static LeaseDto leaseMapperEntityToDto(Lease lease){
        return LeaseDto.builder()
                .totalCost(lease.getTotalCost())
                .leaseDate(lease.getLeaseDate())
                .returnDate(lease.getReturnDate())
                .stockTypeId(lease.getStockType().getId())
                .libraryUserId(lease.getUser().getId())
                .build();
    }

    public static Lease leaseMapperDtoToEntity(LeaseDto leaseDto){
        return Lease.builder()
                .totalCost(leaseDto.getTotalCost())
                .leaseDate(leaseDto.getLeaseDate())
                .returnDate(leaseDto.getReturnDate())
                .build();
    }

    public static List<Integer> mapLeasesFromEntityToDto(List<Lease> leases){
        List<Integer> leasesIds = new ArrayList<>();

        for (Lease entityLease : leases) {
            leasesIds.add(entityLease.getId());
        }
        return leasesIds;
    }

    public static List<Lease> mapLeasesFromDtoToEntity(List<LeaseDto> leaseDtos){
        List<Lease> leases = new ArrayList<>();

        for (LeaseDto dtoLease : leaseDtos) {
            leases.add(leaseMapperDtoToEntity(dtoLease));
        }
        return leases;
    }
}
