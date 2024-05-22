package com.example.Bank.Services.Imp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Bank.Configurations.BankSettings;
import com.example.Bank.Entaties.Account;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Request.WithdrawalDepositRequest;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.OperationsService;
import com.example.Bank.Services.TransferService;

/**
 * Service implementation for performing banking operations.
 * 
 * <p>
 * This class implements the OperationsService interface and provides methods
 * for depositing, withdrawing,
 * and transferring funds between accounts.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to handle banking operations such as deposit,
 * withdrawal, and transfer.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class OperationsServiceImp implements OperationsService {

    private final BankSettings bankSettings;
    /**
     * The repository for accessing account data.
     */
    private final AccountRepository accountRepository;
    /**
     * The repository for accessing account data.
     */
    private final TrasactionServiceImp trasactionService;

    /**
     * Constructs a new OperationsServiceImp instance with the specified
     * AccountRepository and TrasactionServiceImp.
     * 
     * @param accountRepository The repository for accessing account data.
     * @param trasactionService The service for performing transactions.
     */
    public OperationsServiceImp(AccountRepository accountRepository, TrasactionServiceImp trasactionService,
            BankSettings bankSettings) {
        this.accountRepository = accountRepository;
        this.trasactionService = trasactionService;
        this.bankSettings = bankSettings;
    }

    /**
     * Deposits funds into the specified account.
     * 
     * @param contractNumber           The contract number of the account.
     * @param withdrawalDepositRequest The request containing the deposit amount.
     * @return The transaction response representing the deposit transaction.
     * @throws IllegalAccessError if the account does not exist.
     */
    @Override
    public TransactionResponse deposit(String contractNumber, WithdrawalDepositRequest withdrawalDepositRequest) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(contractNumber);
        if (accountOptional.isEmpty()) {
            throw new IllegalAccessError("error");
        }
        Account account = accountOptional.get();
        account.setBalance(account.getBalance() + withdrawalDepositRequest.getAmmount());

        accountRepository.save(account);

        return trasactionService.InsertDeposit(account, withdrawalDepositRequest.getAmmount());
    }

    /**
     * Withdraws funds from the specified account.
     * 
     * @param contractNumber           The contract number of the account.
     * @param withdrawalDepositRequest The request containing the withdrawal amount.
     * @return The transaction response representing the withdrawal transaction.
     * @throws IllegalAccessError if the account does not exist or if the balance is
     *                            insufficient.
     */
    @Override
    public TransactionResponse withdrawal(String contractNumber, WithdrawalDepositRequest withdrawalDepositRequest)
            throws IllegalAccessError {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(contractNumber);

        if (accountOptional.isEmpty()) {
            throw new IllegalAccessError("error");
        }
        Account account = accountOptional.get();
        if (account.getBalance() < withdrawalDepositRequest.getAmmount()) {
            throw new IllegalAccessError("Saldo menor que o pedido");
        }

        account.setBalance(account.getBalance() - withdrawalDepositRequest.getAmmount());
        accountRepository.save(account);
        return trasactionService.InsertWithdrawal(account, withdrawalDepositRequest.getAmmount());

    }

    /**
     * Transfers funds between accounts.
     * 
     * @param contractNumber  The contract number of the source account.
     * @param transferRequest The request containing transfer details.
     * @return The transaction response representing the transfer transaction.
     */

    @Override
    public TransactionResponse Transfer(String contractNumber, TransferRequest transferRequest) {
        TransferService transferService;
        if (transferRequest.getIban().subSequence(0, 8).toString()
                .equals(bankSettings.getCountryCode() + bankSettings.getCheckDigit()
                        + bankSettings.getBankCode())) {
            transferService = new InterTransferServiceImp(accountRepository, trasactionService);
        } else {
            transferService = new OutherBankTransferServiceImp(accountRepository, trasactionService);
        }
        return transferService.Transfer(contractNumber, transferRequest);
    }
}
