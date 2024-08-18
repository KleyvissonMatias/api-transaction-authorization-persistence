package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Account;
import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.StatusCodeTransactionEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.InsufficientBalanceException;
import br.com.caju.api.transaction.authorization.persistence.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final MccService mccService;
    private final StatusTransactionService statusTransactionService;

    @Transactional
    public TransactionDTOResponse authorize(Transaction transactionRequest) {
        Account account = accountService.findByAccountId(transactionRequest.getAccount());

        if (!hasSufficientBalanceForMcc(transactionRequest.getMccCode(), transactionRequest.getTotalAmount(), account)) {
            throw new InsufficientBalanceException("Insufficient balance for operation");
        }

        int rowsUpdated = accountService.updateAccountBalanceByMccType(transactionRequest.getMccCode(), transactionRequest.getTotalAmount(), account);
        saveTransaction(transactionRequest, rowsUpdated);

        return new TransactionDTOResponse(StatusCodeTransactionEnum.APPROVED.getCode());
    }

    private boolean hasSufficientBalanceForMcc(String mccCode, BigDecimal totalAmount, Account account) {
        return accountService.isTransactionBalanceValid(mccCode, totalAmount, account);
    }

    private void saveTransaction(Transaction transactionRequest, int rowsUpdated) {
        Transaction transaction = Transaction.builder()
                .mccType(mccService.findMccById(MccTypeEnum.fromValue(transactionRequest.getMccCode()).ordinal()))
                .statusTransaction(getTransactionStatus(rowsUpdated))
                .account(transactionRequest.getAccount())
                .merchant(transactionRequest.getMerchant())
                .dtCreated(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
    }

    private StatusTransaction getTransactionStatus(int rowsUpdated) {
        return (rowsUpdated > 0)
                ? statusTransactionService.findStatusTransactionById(StatusCodeTransactionEnum.APPROVED.ordinal())
                : statusTransactionService.findStatusTransactionById(StatusCodeTransactionEnum.REJECTED.ordinal());
    }
}
