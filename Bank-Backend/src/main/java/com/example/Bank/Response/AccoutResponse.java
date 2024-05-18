package com.example.Bank.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing an account response.
 * This class encapsulates the necessary information for responding with account
 * details.
 * 
 * @author Pedro Pereira
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccoutResponse {
    /**
     * The account number.
     */
    String accountNumber;
    /**
     * The balance of the account.
     */
    Double balance;
    /**
     * The IBAN (International Bank Account Number) of the account.
     */
    String iban;
    /**
     * The list of transactions associated with the account.
     */
    List<TransactionResponse> transactions;
}
