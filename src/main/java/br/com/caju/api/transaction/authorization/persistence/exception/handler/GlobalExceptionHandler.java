package br.com.caju.api.transaction.authorization.persistence.exception.handler;

import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.InsufficientBalanceException;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<TransactionDTOResponse> handleNotFoundTransaction(NotFoundException ex) {
        TransactionDTOResponse response = new TransactionDTOResponse(StatusCodeTransactionEnum.FAILURE.getCode());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TransactionDTOResponse> handleGeneralException(Exception ex) {
        TransactionDTOResponse response = new TransactionDTOResponse(StatusCodeTransactionEnum.FAILURE.getCode());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<TransactionDTOResponse> handleInsufficientBalance(InsufficientBalanceException ex) {
        TransactionDTOResponse response = new TransactionDTOResponse(StatusCodeTransactionEnum.REJECTED.getCode());
        return ResponseEntity.ok(response);
    }
}
