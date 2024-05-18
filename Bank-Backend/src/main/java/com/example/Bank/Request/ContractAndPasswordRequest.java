package com.example.Bank.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing an email and password request.
 * This class encapsulates the necessary information for authenticating a user
 * with email and password.
 * 
 * @author Pedro Pereira
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractAndPasswordRequest {
    /**
     * The user's contract number to identifier.
     */
    @NotNull
    String contract;
    /**
     * The user's password.
     */
    @NotNull
    String password;
}
