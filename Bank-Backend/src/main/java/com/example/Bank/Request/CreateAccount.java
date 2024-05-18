package com.example.Bank.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for creating a new user account.
 * This class encapsulates the necessary information for creating a new user
 * account.
 * 
 * @author Pedro Pereira
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccount {

    /**
     * The name of the user.
     */
    @NotNull
    String name;
    /**
     * The email address of the user.
     */
    @NotNull
    @Email
    String email;
    /**
     * The password for the user's account.
     */
    @NotNull
    String password;
}
