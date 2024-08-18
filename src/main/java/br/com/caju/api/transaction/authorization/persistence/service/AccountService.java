package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Account;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found for ID: " + accountId));
    }

    @Transactional
    public int updateAccountBalanceByMccType(String mccCode, BigDecimal totalAmount, Account account) {
        BigDecimal updatedBalance;
        switch (MccTypeEnum.fromValue(mccCode)) {
            case FOOD -> updatedBalance = updateBalance(account.getFoodBalance(), totalAmount);
            case MEAL -> updatedBalance = updateBalance(account.getMealBalance(), totalAmount);
            default -> updatedBalance = updateBalance(account.getBalance(), totalAmount);
        }
        return updateAccountBalanceInRepository(mccCode, updatedBalance, account);
    }

    public boolean isTransactionBalanceValid(String mccCode, BigDecimal totalAmount, Account account) {
        BigDecimal balance = switch (MccTypeEnum.fromValue(mccCode)) {
            case FOOD -> account.getFoodBalance();
            case MEAL -> account.getMealBalance();
            default -> account.getBalance();
        };
        return isSufficientBalance(balance, totalAmount);
    }

    private BigDecimal updateBalance(BigDecimal currentBalance, BigDecimal amount) {
        return currentBalance.subtract(amount);
    }

    private int updateAccountBalanceInRepository(String mccCode, BigDecimal updatedBalance, Account account) {
        return switch (MccTypeEnum.fromValue(mccCode)) {
            case FOOD -> accountRepository.updateAccountFoodBalance(account.getId(), updatedBalance);
            case MEAL -> accountRepository.updateAccountMealBalance(account.getId(), updatedBalance);
            default -> accountRepository.updateAccountBalance(account.getId(), updatedBalance);
        };
    }

    private boolean isSufficientBalance(BigDecimal balance, BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }
}

