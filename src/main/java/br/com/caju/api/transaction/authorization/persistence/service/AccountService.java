package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Account;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

    public Account findByAccountId(String accountId) {
        LOGGER.log(Level.INFO, "Searching for account with id: {0}", accountId);
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> {
                    LOGGER.log(Level.WARNING, "Account not found for id: {0}", accountId);
                    return new NotFoundException("Account not found for id: " + accountId);
                });
    }

    @Transactional
    public int updateAccountBalanceByMccType(String mccCode, BigDecimal totalAmount, Account account) {
        LOGGER.log(Level.INFO, "Updating account: {0}", account);

        BigDecimal updatedBalance;
        int rowsAffected;

        switch (MccTypeEnum.fromValue(mccCode)) {
            case FOOD -> {
                if (isSufficientBalance(account.getFoodBalance(), totalAmount)) {
                    updatedBalance = updateBalance(account.getFoodBalance(), totalAmount);
                    rowsAffected = accountRepository.updateAccountFoodBalance(account.getId(), updatedBalance);
                    LOGGER.log(Level.INFO, "Updated food balance for account id: {0}", account.getId());

                } else {
                    updatedBalance = updateBalance(account.getBalance(), totalAmount);
                    rowsAffected = accountRepository.updateAccountBalance(account.getId(), updatedBalance);
                    LOGGER.log(Level.INFO, "Updated general balance for account id: {0} ", account.getId());
                }
            }
            case MEAL -> {
                if (isSufficientBalance(account.getMealBalance(), totalAmount)) {
                    updatedBalance = updateBalance(account.getMealBalance(), totalAmount);
                    rowsAffected = accountRepository.updateAccountMealBalance(account.getId(), updatedBalance);
                    LOGGER.log(Level.INFO, "Updated meal balance for account id: {0}", account.getId());

                } else {
                    updatedBalance = updateBalance(account.getBalance(), totalAmount);
                    rowsAffected = accountRepository.updateAccountBalance(account.getId(), updatedBalance);
                    LOGGER.log(Level.INFO, "Updated general balance for account id: {0} ", account.getId());
                }
            }
            default -> {
                updatedBalance = updateBalance(account.getBalance(), totalAmount);
                rowsAffected = accountRepository.updateAccountBalance(account.getId(), updatedBalance);
            }
        }

        return rowsAffected;
    }

    public boolean hasSufficientBalanceForTransaction(String mccCode, BigDecimal totalAmount, Account account) {
        BigDecimal balance;

        switch (MccTypeEnum.fromValue(mccCode)) {
            case FOOD -> balance = isSufficientBalance(account.getFoodBalance(), totalAmount)
                    ? account.getFoodBalance() : account.getBalance();
            case MEAL -> balance = isSufficientBalance(account.getMealBalance(), totalAmount)
                    ? account.getMealBalance() : account.getBalance();
            default -> balance = account.getBalance();
        }

        boolean hasSufficientBalance = isSufficientBalance(balance, totalAmount);
        LOGGER.log(Level.INFO, "Balance sufficiency check result: {0}", hasSufficientBalance);
        return hasSufficientBalance;
    }

    private BigDecimal updateBalance(BigDecimal currentBalance, BigDecimal amount) {
        return currentBalance.subtract(amount);
    }

    private boolean isSufficientBalance(BigDecimal balance, BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }
}
