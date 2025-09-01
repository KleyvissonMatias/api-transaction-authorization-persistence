package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Account;
import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import br.com.caju.api.transaction.authorization.persistence.domain.StatusTransaction;
import br.com.caju.api.transaction.authorization.persistence.domain.Transaction;
import br.com.caju.api.transaction.authorization.persistence.domain.dto.reponse.TransactionDTOResponse;
import br.com.caju.api.transaction.authorization.persistence.domain.enums.MccTypeEnum;
import br.com.caju.api.transaction.authorization.persistence.domain.enums.StatusCodeTransactionEnum;
import br.com.caju.api.transaction.authorization.persistence.domain.mapper.TransactionMapper;
import br.com.caju.api.transaction.authorization.persistence.exception.InsufficientBalanceException;
import br.com.caju.api.transaction.authorization.persistence.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final MccService mccService;
    private final StatusTransactionService statusTransactionService;
    private final TransactionMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(TransactionService.class.getName());

    @Transactional
    public TransactionDTOResponse authorize(Transaction transactionRequest) {
        LOGGER.log(Level.INFO, "Starting authorization for transaction: {0}", transactionRequest);

        Account account = accountService.findByAccountId(transactionRequest.getAccount());
        LOGGER.log(Level.INFO, "Retrieved account: {0}", account);

        if (!accountService.hasSufficientBalanceForTransaction(transactionRequest.getMccCode(), transactionRequest.getTotalAmount(), account)) {
            LOGGER.log(Level.WARNING, "Insufficient balance for transaction: {0}", transactionRequest);

            throw new InsufficientBalanceException("Insufficient balance for operation");
        }

        int rowsUpdated = accountService.updateAccountBalanceByMccType(transactionRequest.getMccCode(), transactionRequest.getTotalAmount(), account);
        LOGGER.log(Level.INFO, "Account balance updated, rows affected: {0}", rowsUpdated);

        saveTransaction(transactionRequest, rowsUpdated);

        return new TransactionDTOResponse(StatusCodeTransactionEnum.APPROVED.getCode());
    }

    private void saveTransaction(Transaction transactionRequest, int rowsUpdated) {
        LOGGER.log(Level.INFO, "Saving transaction: {0}", transactionRequest);
        Mcc mccType = mccService.findMccById(MccTypeEnum.fromValue(transactionRequest.getMccCode()).ordinal());
        StatusTransaction statusTransaction = statusTransactionService.getTransactionStatus(rowsUpdated);

        Transaction transaction = mapper.toEntityResponse(transactionRequest, mccType, statusTransaction);

        transactionRepository.save(transaction);
        LOGGER.log(Level.INFO, "Transaction saved successfully.");
    }
}