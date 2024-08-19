package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Account;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.caju.api.transaction.authorization.persistence.domain.Mock.getAccountInsufficienteBalanceMock;
import static br.com.caju.api.transaction.authorization.persistence.domain.Mock.getAccountMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    private Account accountInsufficienteBalance;

    @BeforeEach
    void setUp() {
        account = getAccountMock();
        accountInsufficienteBalance = getAccountInsufficienteBalanceMock();
    }

    @Test
    void testFindByAccountId_Success() {
        when(accountRepository.findByAccountId("12345")).thenReturn(Optional.of(account));

        Account foundAccount = accountService.findByAccountId("12345");

        assertEquals(account, foundAccount);
    }

    @Test
    void testFindByAccountId_NotFound() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> accountService.findByAccountId(anyString()));
    }

    @Test
    void testHasSufficientBalanceForTransactionFood_Sufficient() {
        boolean result = accountService.hasSufficientBalanceForTransaction("5411", BigDecimal.valueOf(40), account);

        assertTrue(result);
    }

    @Test
    void testHasSufficientBalanceForTransactionFood_Insufficient() {
        boolean result = accountService.hasSufficientBalanceForTransaction("5411", BigDecimal.valueOf(60), accountInsufficienteBalance);

        assertFalse(result);
    }

    @Test
    void testHasSufficientBalanceForTransactionMeal_Sufficient() {
        boolean result = accountService.hasSufficientBalanceForTransaction("5812", BigDecimal.valueOf(15), account);

        assertTrue(result);
    }

    @Test
    void testHasSufficientBalanceForTransactionMeal_Insufficient() {
        boolean result = accountService.hasSufficientBalanceForTransaction("5812", BigDecimal.valueOf(25), accountInsufficienteBalance);

        assertFalse(result);
    }

    @Test
    void testHasSufficientBalanceForTransactionOther_Sufficient() {
        boolean result = accountService.hasSufficientBalanceForTransaction("5817", BigDecimal.valueOf(80), account);

        assertTrue(result);
    }

    @Test
    void testHasSufficientBalanceForTransactionOther_Insufficient() {
        boolean result = accountService.hasSufficientBalanceForTransaction("", BigDecimal.valueOf(120), account);

        assertFalse(result);
    }
}