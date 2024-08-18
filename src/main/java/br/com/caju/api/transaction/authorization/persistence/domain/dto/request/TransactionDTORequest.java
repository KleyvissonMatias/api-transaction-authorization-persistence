package br.com.caju.api.transaction.authorization.persistence.domain.dto.request;

import java.math.BigDecimal;

public record TransactionDTORequest(String account, BigDecimal totalAmount, String mcc, String merchant) {
}
