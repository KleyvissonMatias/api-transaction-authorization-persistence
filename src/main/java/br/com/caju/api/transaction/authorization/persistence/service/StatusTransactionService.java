package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.StatusTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatusTransactionService {

    private final StatusTransactionRepository statusTransactionRepository;

    public StatusTransaction findStatusTransactionById(Integer id) {
        return statusTransactionRepository.findStatusTransactionById(id)
                .orElseThrow(() -> new NotFoundException("StatusTransaction not found for ID: " + id));
    }
}
