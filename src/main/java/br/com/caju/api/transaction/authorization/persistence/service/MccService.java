package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.MccRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class MccService {

    private final MccRepository mccRepository;

    private static final Logger LOGGER = Logger.getLogger(MccService.class.getName());

    public Mcc findMccById(Integer id) {
        return mccRepository.findMccById(id)
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, "Mcc not found for id: {0}", id);
                    return new NotFoundException("Mcc not found for id: " + id);
                });
    }
}
