package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.InsufficientBalanceException;
import br.com.caju.api.transaction.authorization.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.caju.api.transaction.authorization.persistence.domain.Mock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private MccService mccService;

    @Mock
    private StatusTransactionService statusTransactionService;

    @InjectMocks
    private TransactionService transactionService;


    @Test
    void testAuthorizeFood_Success() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(true);
        when(accountService.updateAccountBalanceByMccType(anyString(), any(), any())).thenReturn(1);
        when(mccService.findMccById(anyInt())).thenReturn(getMccFoodMock());
        when(statusTransactionService.findStatusTransactionById(anyInt())).thenReturn(getStatusTransactionApprovedMock());

        TransactionDTOResponse response = transactionService.authorize(getTransactionFoodMock());

        assertEquals(StatusCodeTransactionEnum.APPROVED.getCode(), response.code());

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testAuthorizeFood_InsufficientBalanceException() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountInsufficienteBalanceMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(false);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.authorize(getTransactionFoodMock()));

        verify(transactionRepository, never()).save(any());
    }

    @Test
    void testAuthorizeMeal_Success() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(true);
        when(accountService.updateAccountBalanceByMccType(anyString(), any(), any())).thenReturn(1);
        when(mccService.findMccById(anyInt())).thenReturn(getMccMealMock());
        when(statusTransactionService.findStatusTransactionById(anyInt())).thenReturn(getStatusTransactionApprovedMock());

        TransactionDTOResponse response = transactionService.authorize(getTransactionMealMock());

        assertEquals(StatusCodeTransactionEnum.APPROVED.getCode(), response.code());

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testAuthorizeMeal_InsufficientBalanceException() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountInsufficienteBalanceMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(false);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.authorize(getTransactionMealMock()));

        verify(transactionRepository, never()).save(any());
    }

    @Test
    void testAuthorizeCash_Success() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(true);
        when(accountService.updateAccountBalanceByMccType(anyString(), any(), any())).thenReturn(1);
        when(mccService.findMccById(anyInt())).thenReturn(getMccFoodMock());
        when(statusTransactionService.findStatusTransactionById(anyInt())).thenReturn(getStatusTransactionApprovedMock());

        TransactionDTOResponse response = transactionService.authorize(getTransactionFoodMock());

        assertEquals(StatusCodeTransactionEnum.APPROVED.getCode(), response.code());

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testAuthorizeCashMccCodeToMerchant_Success() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(true);
        when(accountService.updateAccountBalanceByMccType(anyString(), any(), any())).thenReturn(1);
        when(mccService.findMccById(anyInt())).thenReturn(getMccFoodMock());
        when(statusTransactionService.findStatusTransactionById(anyInt())).thenReturn(getStatusTransactionApprovedMock());

        TransactionDTOResponse response = transactionService.authorize(getTransactionMccCodeInvalidMock());

        assertEquals(StatusCodeTransactionEnum.APPROVED.getCode(), response.code());

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testAuthorizeCash_InsufficientBalanceException() {
        when(accountService.findByAccountId(anyString())).thenReturn(getAccountInsufficienteBalanceMock());
        when(accountService.hasSufficientBalanceForTransaction(anyString(), any(), any())).thenReturn(false);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.authorize(getTransactionFoodMock()));

        verify(transactionRepository, never()).save(any());
    }
}
