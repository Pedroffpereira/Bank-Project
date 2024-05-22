package com.example.Bank.Services.Imp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.TransferService;

/**
 * Service implementation for performing outher-account transfers.
 * 
 * <p>
 * This class implements the TransferService interface and provides a method for
 * transferring funds
 * between accounts.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to handle outher-account transfers.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class OutherBankTransferServiceImp implements TransferService {
    /**
     * The repository for accessing account data.
     */
    private final AccountRepository accountRepository;
    /**
     * The service for performing transactions.
     */
    private final TrasactionServiceImp trasactionService;

    /**
     * Constructs a new InterTransferServiceImp instance with the specified
     * AccountRepository and TrasactionServiceImp.
     * 
     * @param accountRepository The repository for accessing account data.
     * @param trasactionService The service for performing transactions.
     */
    public OutherBankTransferServiceImp(AccountRepository accountRepository, TrasactionServiceImp trasactionService) {
        this.accountRepository = accountRepository;
        this.trasactionService = trasactionService;
    }

    /**
     * Transfers funds from one account to another.
     * 
     * @param contractNumber  The contract number of the source account.
     * @param transferRequest The request containing transfer details.
     * @return The transaction response representing the transfer transaction.
     * @throws IllegalAccessError if the source or destination account does not
     *                            exist, or if the balance in the source account is
     *                            insufficient.
     */
    @Override
    public TransactionResponse Transfer(String contractNumber, TransferRequest transferRequest) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(contractNumber);

        if (accountOptional.isEmpty()) {
            throw new IllegalAccessError("Conta não existe");
        }
        Account account = accountOptional.get();

        if (account.getBalance() < transferRequest.getAmmount()) {
            throw new IllegalAccessError("Montante da operação e maior do que tem na conta");
        }

        account.setBalance(account.getBalance() - transferRequest.getAmmount());

        accountRepository.save(account);
        return trasactionService.insertOutTransfer(account, transferRequest.getAmmount());
    }
}
