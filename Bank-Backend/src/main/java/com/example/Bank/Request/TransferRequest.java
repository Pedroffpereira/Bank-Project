package com.example.Bank.Request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a transfer request.
 * This class encapsulates the necessary information for transferring funds.
 * 
 * @author Pedro Pereira
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    /**
     * The IBAN (International Bank Account Number) of the recipient.
     */
    String iban;
    /**
     * The amount to be transferred.
     */
    @Min(value = 0)
    Double ammount;
}
