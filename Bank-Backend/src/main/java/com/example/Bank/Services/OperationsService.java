package com.example.Bank.Services;

import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Request.WithdrawalDepositRequest;
import com.example.Bank.Response.TransactionResponse;

/**
 * Service interface for managing banking operations.
 * 
 * <p>
 * This interface declares methods for performing deposit, withdrawal, and
 * transfer operations.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define banking operation management
 * operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
public interface OperationsService {
    /**
     * Performs a deposit operation for the specified account.
     * 
     * @param contractNumber           The contract number associated with the
     *                                 account.
     * @param withdrawalDepositRequest The request containing details of the deposit
     *                                 operation.
     * @return A TransactionResponse object representing the result of the deposit
     *         operation.
     */
    public TransactionResponse deposit(String contractNumber, WithdrawalDepositRequest withdrawalDepositRequest);

    /**
     * Performs a withdrawal operation for the specified account.
     * 
     * @param contractNumber           The contract number associated with the
     *                                 account.
     * @param withdrawalDepositRequest The request containing details of the
     *                                 withdrawal operation.
     * @return A TransactionResponse object representing the result of the
     *         withdrawal operation.
     */
    public TransactionResponse withdrawal(String contractNumber, WithdrawalDepositRequest withdrawalDepositRequest);

    /**
     * Performs a transfer operation between two accounts.
     * 
     * @param contractNumber  The contract number associated with the source
     *                        account.
     * @param transferRequest The request containing details of the transfer
     *                        operation.
     * @return A TransactionResponse object representing the result of the transfer
     *         operation.
     */
    public TransactionResponse Transfer(String contractNumber, TransferRequest transferRequest);
}
