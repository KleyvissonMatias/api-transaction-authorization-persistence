package br.com.caju.api.transaction.authorization.persistence.config;

import br.com.caju.api.transaction.authorization.persistence.domain.mapper.TransactionMapper;
import br.com.caju.api.transaction.authorization.persistence.presentation.TransactionController;
import br.com.caju.api.transaction.authorization.persistence.service.TransactionService;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfiguration {

    @Bean
    TransactionController transactionController(TransactionService service, TransactionMapper mapper) {
        return new TransactionController(service, mapper);
    }

    @Bean
    TransactionMapper transactionMapper() {
        return Mappers.getMapper(TransactionMapper.class);
    }
}
