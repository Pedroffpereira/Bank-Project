package com.example.Bank.Services;

import org.springframework.stereotype.Service;

import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Response.TransactionResponse;

/**
 * Service interface for managing transfer-related operations.
 * 
 * <p>
 * This interface declares a method for performing a transfer transaction.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define transfer management operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public interface TransferService {
    /**
     * Performs a transfer transaction.
     * 
     * <p>
     * This method transfers the specified amount from the account associated with
     * the provided contract number to another account based on the details in the
     * transfer request.
     * </p>
     * 
     * @param contractNumber  The contract number associated with the source
     *                        account.
     * @param transferRequest The transfer request containing the details of the
     *                        transfer.
     * @return A TransactionResponse object representing the result of the transfer
     *         transaction.
     */
    public TransactionResponse Transfer(String contractNumber, TransferRequest transferRequest);
}
