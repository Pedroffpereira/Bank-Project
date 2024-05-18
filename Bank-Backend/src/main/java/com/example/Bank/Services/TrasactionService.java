package com.example.Bank.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Response.TransactionResponse;

/**
 * Service interface for managing transaction-related operations.
 * 
 * <p>
 * This interface declares methods for retrieving transactions, inserting
 * transfers,
 * withdrawals, and deposits.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define transaction management operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public interface TrasactionService {
    /**
     * Retrieves a list of transactions associated with the specified account
     * number.
     * 
     * @param accountNumber The account number for which transactions are to be
     *                      retrieved.
     * @param page          The page number of the result set to retrieve.
     * @param size          The size of each page in the result set.
     * @return A list of TransactionResponse objects representing the transactions.
     */
    public List<TransactionResponse> getTransaction(String accountNumber, Integer page, Integer size);

    /**
     * Inserts a transfer transaction between two accounts.
     * 
     * @param account         The source account from which the transfer is made.
     * @param amount          The amount to transfer.
     * @param transferAccount The destination account to which the transfer is made.
     * @return A TransactionResponse object representing the inserted transfer
     *         transaction.
     */
    public TransactionResponse insertTransfer(Account account, Double amount, Account transferAccount);

    /**
     * Inserts a withdrawal transaction for the specified account.
     * 
     * @param account The account from which the withdrawal is made.
     * @param amount  The amount to withdraw.
     * @return A TransactionResponse object representing the inserted withdrawal
     *         transaction.
     */
    public TransactionResponse InsertWithdrawal(Account account, Double amount);

    /**
     * Inserts a deposit transaction for the specified account.
     * 
     * @param account The account to which the deposit is made.
     * @param amount  The amount to deposit.
     * @return A TransactionResponse object representing the inserted deposit
     *         transaction.
     */
    public TransactionResponse InsertDeposit(Account account, Double amount);
}
