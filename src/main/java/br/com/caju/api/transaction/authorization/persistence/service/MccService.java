package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.MccRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MccService {

    private final MccRepository mccRepository;

    public Mcc findMccById(Integer id) {
        return mccRepository.findMccById(id)
                .orElseThrow(() -> new NotFoundException("MCC not found for ID: " + id));
    }
}
