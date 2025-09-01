package br.com.caju.api.transaction.authorization.persistence.domain.mapper;

import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.request.TransactionDTORequest;
import br.com.caju.api.transaction.authorization.persistence.utils.MccUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper
public interface TransactionMapper {

    @Mapping(target = "account", source = "transactionDTORequest.account")
    @Mapping(target = "totalAmount", source = "transactionDTORequest.totalAmount")
    @Mapping(target = "mccCode", source = "transactionDTORequest.mcc")
    @Mapping(target = "merchant", source = "transactionDTORequest.merchant")
    Transaction toEntity(TransactionDTORequest transactionDTORequest);

    default Transaction toEntityResponse(Transaction transactionRequest, Mcc mccType, StatusTransaction statusTransaction) {
        return Transaction.builder()
                .mccType(mccType)
                .mccCode(MccUtils.getMerchantOrMcc(transactionRequest.getMccCode(), transactionRequest.getMerchant()))
                .statusTransaction(statusTransaction)
                .account(transactionRequest.getAccount())
                .totalAmount(transactionRequest.getTotalAmount())
                .merchant(transactionRequest.getMerchant())
                .dtCreated(LocalDateTime.now())
                .build();
    }
}