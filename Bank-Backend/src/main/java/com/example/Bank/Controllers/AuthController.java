package com.example.Bank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Request.ContractAndPasswordRequest;
import com.example.Bank.Request.CreateAccount;
import com.example.Bank.Response.AccoutResponse;
import com.example.Bank.Response.CreateAccountResponse;
import com.example.Bank.Response.JWTResponse;

import jakarta.validation.Valid;

/**
 * Interface for handling authentication-related endpoints.
 * 
 * This interface defines methods for handling login, registration, and
 * authentication
 * endpoints in the application.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/users")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public interface AuthController {

    /**
     * Handle user login.
     * 
     * This method handles the login endpoint. It takes an EmailAndPasswordRequest
     * object containing the user's email and password, validates it, and returns
     * a JWTResponse containing the authentication token.
     * 
     * @param contractAndPasswordRequest The request containing user contract and
     *                                   password.
     * @return A ResponseEntity containing the JWTResponse with the authentication
     *         token.
     */
    public ResponseEntity<JWTResponse> login(@RequestBody @Valid ContractAndPasswordRequest contractAndPasswordRequest);

    /**
     * Handle user registration.
     * 
     * This method handles the registration endpoint. It takes a CreateAccount
     * object
     * containing the user's registration information, validates it, creates a new
     * account,
     * and returns a CreateAccountResponse with the details of the created account.
     * 
     * @param createAccount The request containing user registration information.
     * @return A ResponseEntity containing the CreateAccountResponse with the
     *         details of the created account.
     */
    public ResponseEntity<CreateAccountResponse> registation(@RequestBody @Valid CreateAccount createAccount);

    /**
     * Handle user authentication.
     * 
     * This method handles the authentication endpoint. It takes a JWT object
     * containing
     * the authentication token, verifies it, and returns details of the
     * authenticated user's account.
     * 
     * @param jwt The JWT token containing authentication information.
     * @return A ResponseEntity containing the AccoutResponse with details of the
     *         authenticated user's account.
     */
    public ResponseEntity<AccoutResponse> auth(@AuthenticationPrincipal Jwt jwt);
}
