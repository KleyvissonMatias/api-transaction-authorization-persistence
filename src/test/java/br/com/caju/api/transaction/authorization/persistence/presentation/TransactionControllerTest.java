package br.com.caju.api.transaction.authorization.persistence.presentation;

import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.request.TransactionDTORequest;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;
import br.com.caju.api.transaction.authorization.persistence.domain.mapper.TransactionMapper;
import br.com.caju.api.transaction.authorization.persistence.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void testAuthorizeApproved_Success() {
        TransactionDTORequest request = new TransactionDTORequest("12345", BigDecimal.TEN, "5411", "IFOOD                    PERNAMBUCO BR");
        Transaction transaction = Transaction.builder().build();
        TransactionDTOResponse expectedResponse = new TransactionDTOResponse(StatusCodeTransactionEnum.APPROVED.getCode());

        when(transactionMapper.toEntity(request)).thenReturn(transaction);
        when(transactionService.authorize(transaction)).thenReturn(expectedResponse);

        ResponseEntity<TransactionDTOResponse> response = transactionController.authorize(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(transactionMapper).toEntity(request);
        verify(transactionService).authorize(transaction);
    }

    @Test
    void testAuthorizeRejected_Success() {
        TransactionDTORequest request = new TransactionDTORequest("12345", BigDecimal.TEN, "5411", "IFOOD                    PERNAMBUCO BR");
        Transaction transaction = Transaction.builder().build();
        TransactionDTOResponse expectedResponse = new TransactionDTOResponse(StatusCodeTransactionEnum.REJECTED.getCode());

        when(transactionMapper.toEntity(request)).thenReturn(transaction);
        when(transactionService.authorize(transaction)).thenReturn(expectedResponse);

        ResponseEntity<TransactionDTOResponse> response = transactionController.authorize(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(transactionMapper).toEntity(request);
        verify(transactionService).authorize(transaction);
    }

    @Test
    void testAuthorizeFailure_Success() {
        TransactionDTORequest request = new TransactionDTORequest("12345", BigDecimal.TEN, "5411", "IFOOD                    PERNAMBUCO BR");
        Transaction transaction = Transaction.builder().build();
        TransactionDTOResponse expectedResponse = new TransactionDTOResponse(StatusCodeTransactionEnum.FAILURE.getCode());

        when(transactionMapper.toEntity(request)).thenReturn(transaction);
        when(transactionService.authorize(transaction)).thenReturn(expectedResponse);

        ResponseEntity<TransactionDTOResponse> response = transactionController.authorize(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(transactionMapper).toEntity(request);
        verify(transactionService).authorize(transaction);
    }
}
