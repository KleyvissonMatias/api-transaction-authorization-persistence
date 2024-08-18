package br.com.caju.api.transaction.authorization.persistence.domain.enuns;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum StatusCodeTransactionEnum {

    APPROVED("00", "APROVADO"),
    REJECTED("51", "REJEITADO"),
    FAILURE("07", "FALHA_NO_PROCESSAMENTO");

    private final String code;
    private final String description;

    StatusCodeTransactionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Optional<StatusCodeTransactionEnum> fromCodeText(String code) {
        return Arrays.stream(values())
                .filter(statusCodeTransactionEnum -> statusCodeTransactionEnum.code.equalsIgnoreCase(code))
                .findFirst();
    }
}
