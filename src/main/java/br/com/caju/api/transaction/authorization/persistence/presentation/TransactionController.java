package br.com.caju.api.transaction.authorization.persistence.presentation;

import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.request.TransactionDTORequest;
import br.com.caju.api.transaction.authorization.persistence.domain.mapper.TransactionMapper;
import br.com.caju.api.transaction.authorization.persistence.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionDTOResponse> authorizeTransaction(@Valid @RequestBody TransactionDTORequest transactionRequest) {
        TransactionDTOResponse response = transactionService.authorize(transactionMapper.toEntity(transactionRequest));
        return ResponseEntity.ok(response);
    }
}
