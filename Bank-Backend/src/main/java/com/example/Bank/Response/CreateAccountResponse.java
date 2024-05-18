package com.example.Bank.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a response to account creation.
 * This class encapsulates the necessary information for responding with account
 * creation details.
 * 
 * @author Pedro Pereira
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateAccountResponse {
    /**
     * The IBAN (International Bank Account Number) of the created account.
     */
    String iban;
    /**
     * The contract number or identifier of the created account.
     */
    String contractNumber;
}
