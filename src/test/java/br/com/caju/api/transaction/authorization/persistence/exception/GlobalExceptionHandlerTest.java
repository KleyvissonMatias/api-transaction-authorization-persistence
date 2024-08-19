package br.com.caju.api.transaction.authorization.persistence.exception;

import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleNotFoundTransaction() {
        NotFoundException exception = new NotFoundException("Not found");
        ResponseEntity<TransactionDTOResponse> response = exceptionHandler.handleNotFoundTransaction(exception);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(StatusCodeTransactionEnum.FAILURE.getCode(), Objects.requireNonNull(response.getBody()).code());
    }

    @Test
    void testHandleGeneralException() {
        Exception exception = new RuntimeException("Generic error");
        ResponseEntity<TransactionDTOResponse> response = exceptionHandler.handleGeneralException(exception);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(StatusCodeTransactionEnum.FAILURE.getCode(), Objects.requireNonNull(response.getBody()).code());
    }

    @Test
    void testHandleInsufficientBalance() {
        InsufficientBalanceException exception = new InsufficientBalanceException("Insufficient balance");
        ResponseEntity<TransactionDTOResponse> response = exceptionHandler.handleInsufficientBalance(exception);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(StatusCodeTransactionEnum.REJECTED.getCode(), Objects.requireNonNull(response.getBody()).code());
    }
}
