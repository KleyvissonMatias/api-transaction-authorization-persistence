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

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    private static final Logger LOGGER = Logger.getLogger(TransactionController.class.getName());

    @PostMapping(value = "/authorize")
    public ResponseEntity<TransactionDTOResponse> authorize(@Valid @RequestBody TransactionDTORequest transactionRequest) {
        LOGGER.log(Level.INFO, "Received authorization request: {0}", transactionRequest);
        TransactionDTOResponse response = transactionService.authorize(transactionMapper.toEntity(transactionRequest));
        LOGGER.log(Level.INFO, "Authorization response: {0}", response);

        return ResponseEntity.ok(response);
    }
}
