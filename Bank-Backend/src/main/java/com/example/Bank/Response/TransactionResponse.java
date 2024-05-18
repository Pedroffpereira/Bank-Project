package com.example.Bank.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a transaction response.
 * This class encapsulates the necessary information for responding with
 * transaction details.
 * 
 * @author [Your Name]
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionResponse {
    /**
     * The description of the transaction.
     */
    String description;
    /**
     * The amount of the transaction.
     */
    Double amount;
    /**
     * The balance after the transaction.
     */
    Double balance_after_transaction;
    /**
     * The IBAN (International Bank Account Number) associated with the transaction.
     */
    String iban;
}
