package com.example.Bank.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @NotBlank
    @Size(min = 16, max = 36)
    String iban;
    /**
     * The amount to be transferred.
     */
    @NotNull
    @Min(value = 5)
    Double ammount;
}
