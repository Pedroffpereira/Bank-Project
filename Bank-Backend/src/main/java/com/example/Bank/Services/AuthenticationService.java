package com.example.Bank.Services;

import com.example.Bank.Request.CreateAccount;
import com.example.Bank.Response.AccoutResponse;
import com.example.Bank.Response.CreateAccountResponse;
import com.example.Bank.Response.JWTResponse;

/**
 * Service interface for user authentication and account management.
 * 
 * <p>
 * This interface declares methods for user login, account registration,
 * and authentication.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define authentication and account
 * management operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
public interface AuthenticationService {
    /**
     * Authenticates a user based on the provided username and password.
     * 
     * @param username The username of the user attempting to login.
     * @param password The password associated with the username.
     * @return A JWTResponse object representing the authentication result.
     */
    public JWTResponse loginUser(String username, String password);

    /**
     * Registers a new account based on the provided account details.
     * 
     * @param createAccount The request containing details of the new account to be
     *                      registered.
     * @return A CreateAccountResponse object representing the result of the
     *         registration process.
     */
    public CreateAccountResponse RegistrationAccount(CreateAccount createAccount);

    /**
     * Authenticates a user based on the provided contract number.
     * 
     * @param contractNumber The contract number associated with the user.
     * @return An AccoutResponse object representing the authenticated user's
     *         account information.
     */
    public AccoutResponse auth(String contractNumber);
}