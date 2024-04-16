package com.files.library.service;

import com.files.library.model.LeaseDto;
import com.files.library.model.domain.Lease;
import com.files.library.repository.LeaseRepository;
import com.files.library.service.impl.LeaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class LeaseServiceTest {

    @Mock
    private LeaseRepository leaseRepository;

    @InjectMocks
    private LeaseServiceImpl leaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLeasesByUserId_ReturnsListOfLeases() {

        int userId = 1;
        List<Lease> leases = List.of(
                new Lease(1, null, null, 10.0, null, null),
                new Lease(2, null, null, 20.0, null, null)
        );


        when(leaseRepository.findByUserId(userId)).thenReturn(leases);


        List<LeaseDto> result = leaseService.getLeasesByUserId(userId);


        verify(leaseRepository, times(1)).findByUserId(userId);
        verifyNoMoreInteractions(leaseRepository);

        assertEquals(leases.size(), result.size());
        assertTrue(result.containsAll(leases));
    }

    @Test
    void getLeasesByStockId_ReturnsListOfLeases() {

        int stockId = 1;
        List<Lease> leases = List.of(
                new Lease(1, null, null, 10.0, null, null),
                new Lease(2, null, null, 20.0, null, null)
        );


        when(leaseRepository.findByStockTypeId(stockId)).thenReturn(leases);


        List<LeaseDto> result = leaseService.getLeasesByStockId(stockId);


        verify(leaseRepository, times(1)).findByStockTypeId(stockId);
        verifyNoMoreInteractions(leaseRepository);

        assertEquals(leases.size(), result.size());
        assertTrue(result.containsAll(leases));
    }

    @Test
    void deleteLeaseById_LeaseExists_SuccessfullyDeleted() {

        int id = 1;


        when(leaseRepository.findById(id)).thenReturn(Optional.of(new Lease()));


        String result = leaseService.deleteLeaseById(id);


        verify(leaseRepository, times(1)).findById(id);
        verify(leaseRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(leaseRepository);

        assertEquals("Lease successfully deleted.", result);
    }

    @Test
    void deleteLeaseById_LeaseDoesNotExist_ReturnsErrorMessage() {

        int id = 1;


        when(leaseRepository.findById(id)).thenReturn(Optional.empty());


        String result = leaseService.deleteLeaseById(id);


        verify(leaseRepository, times(1)).findById(id);
        verifyNoMoreInteractions(leaseRepository);

        assertEquals("Couldn't find the lease with id: " + id, result);
    }

    @Test
    void modifyLeaseById_LeaseExists_SuccessfullyUpdated() {

        int id = 1;
        LeaseDto modifiedLease = new LeaseDto();


        when(leaseRepository.findById(id)).thenReturn(Optional.of(new Lease()));


        String result = leaseService.modifyLeaseById(id, modifiedLease);


        verify(leaseRepository, times(1)).findById(id);
        verify(leaseRepository, times(1)).save(any(Lease.class));
        verifyNoMoreInteractions(leaseRepository);

        assertEquals("Lease with id " + id + " successfully updated.", result);
    }

    @Test
    void modifyLeaseById_LeaseDoesNotExist_ReturnsErrorMessage() {

        int id = 1;
        LeaseDto modifiedLease = new LeaseDto();


        when(leaseRepository.findById(id)).thenReturn(Optional.empty());


        String result = leaseService.modifyLeaseById(id, modifiedLease);


        verify(leaseRepository, times(1)).findById(id);
        verifyNoMoreInteractions(leaseRepository);

        assertEquals("Lease with id " + id + " does not exists in our DB. Please, type a existent id.", result);
    }
}