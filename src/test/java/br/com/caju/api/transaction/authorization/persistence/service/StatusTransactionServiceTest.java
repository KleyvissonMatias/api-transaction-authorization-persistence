package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.StatusTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.caju.api.transaction.authorization.persistence.domain.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatusTransactionServiceTest {

    @Mock
    private StatusTransactionRepository repository;

    @InjectMocks
    private StatusTransactionService service;

    @Test
    void testFindStatusTransacationByIdApproved_Success() {
        when(repository.findStatusTransactionById(anyInt())).thenReturn(Optional.of(getStatusTransactionApprovedMock()));

        StatusTransaction response = service.findStatusTransactionById(0);

        assertNotNull(response);
        assertEquals(response.getCodeStatusTransactionEnum().getCode(), "00");
    }

    @Test
    void testFindStatusTransacationByIdRejected_Success() {
        when(repository.findStatusTransactionById(anyInt())).thenReturn(Optional.of(getStatusTransactionRejectedMock()));

        StatusTransaction response = service.findStatusTransactionById(1);

        assertNotNull(response);
        assertEquals(response.getCodeStatusTransactionEnum().getCode(), "51");
    }

    @Test
    void testFindMccByIdFailure_Success() {
        when(repository.findStatusTransactionById(anyInt())).thenReturn(Optional.of(getStatusTransactionFailureMock()));

        StatusTransaction response = service.findStatusTransactionById(1);

        assertNotNull(response);
        assertEquals(response.getCodeStatusTransactionEnum().getCode(), "07");
    }

    @Test
    void testFindMccById_NotFoundException() {
        when(repository.findStatusTransactionById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findStatusTransactionById(anyInt()));
    }
}
