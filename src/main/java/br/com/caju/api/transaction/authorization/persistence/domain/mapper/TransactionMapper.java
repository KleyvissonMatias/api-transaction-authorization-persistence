package br.com.caju.api.transaction.authorization.persistence.domain.mapper;

import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.request.TransactionDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransactionMapper {

    @Mapping(target = "account", source = "transactionDTORequest.account")
    @Mapping(target = "totalAmount", source = "transactionDTORequest.totalAmount")
    @Mapping(target = "mccCode", source = "transactionDTORequest.mcc")
    @Mapping(target = "merchant", source = "transactionDTORequest.merchant")
    Transaction toEntity(TransactionDTORequest transactionDTORequest);
}
