package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.StatusTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class StatusTransactionService {

    private final StatusTransactionRepository statusTransactionRepository;

    private static final Logger LOGGER = Logger.getLogger(StatusTransactionService.class.getName());

    public StatusTransaction findStatusTransactionById(Integer id) {
        return statusTransactionRepository.findStatusTransactionById(id)
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, "StatusTransaction not found for id: {0}", id);
                    return new NotFoundException("StatusTransaction not found for id:" + id);
                });
    }
}
