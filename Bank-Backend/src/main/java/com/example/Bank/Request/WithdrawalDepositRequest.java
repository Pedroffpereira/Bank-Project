package com.example.Bank.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a withdrawal or deposit request.
 * This class encapsulates the necessary information for withdrawing or
 * depositing funds.
 * 
 * @author Pedro Pereira
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalDepositRequest {
    /**
     * The amount to be withdrawn or deposited.
     */
    @NotNull
    @Min(value = 5)
    Double ammount;
}
