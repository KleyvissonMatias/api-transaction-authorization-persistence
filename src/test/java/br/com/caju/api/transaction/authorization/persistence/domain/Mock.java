package br.com.caju.api.transaction.authorization.persistence.domain;

import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;

import java.math.BigDecimal;

public class Mock {

    public static Transaction getTransactionFoodMock() {
        return Transaction.builder()
                .account("12345")
                .totalAmount(BigDecimal.valueOf(50))
                .mccCode("5411")
                .merchant("Restaurant XYZ")
                .build();
    }

    public static Transaction getTransactionMccCodeInvalidMock() {
        return Transaction.builder()
                .account("12345")
                .mccCode("6543")
                .totalAmount(BigDecimal.valueOf(50))
                .merchant("Restaurant XYZ")
                .build();
    }

    public static Transaction getTransactionMealMock() {
        return Transaction.builder()
                .account("12345")
                .totalAmount(BigDecimal.valueOf(50))
                .mccCode("5811")
                .merchant("Restaurant XYZ")
                .build();
    }

    public static Transaction getTransactionCashMock() {
        return Transaction.builder()
                .account("12345")
                .totalAmount(BigDecimal.valueOf(50))
                .merchant("Restaurant XYZ")
                .build();
    }

    public static Mcc getMccFoodMock() {
        return Mcc.builder()
                .id(1)
                .category(MccTypeEnum.FOOD)
                .build();
    }

    public static Mcc getMccMealMock() {
        return Mcc.builder()
                .id(1)
                .category(MccTypeEnum.MEAL)
                .build();
    }

    public static Mcc getMccCashMock() {
        return Mcc.builder()
                .id(1)
                .category(MccTypeEnum.CASH)
                .build();
    }

    public static StatusTransaction getStatusTransactionApprovedMock() {
        return StatusTransaction.builder()
                .id(1)
                .codeStatusTransactionEnum(StatusCodeTransactionEnum.APPROVED)
                .build();
    }

    public static StatusTransaction getStatusTransactionRejectedMock() {
        return StatusTransaction.builder()
                .id(2)
                .codeStatusTransactionEnum(StatusCodeTransactionEnum.REJECTED)
                .build();
    }

    public static StatusTransaction getStatusTransactionFailureMock() {
        return StatusTransaction.builder()
                .id(2)
                .codeStatusTransactionEnum(StatusCodeTransactionEnum.FAILURE)
                .build();
    }

    public static Account getAccountMock() {
        return Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(100))
                .foodBalance(BigDecimal.valueOf(100))
                .mealBalance(BigDecimal.valueOf(30))
                .build();
    }

    public static Account getAccountInsufficienteBalanceMock() {
        return Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(0))
                .foodBalance(BigDecimal.valueOf(0))
                .mealBalance(BigDecimal.valueOf(0))
                .build();
    }
}
