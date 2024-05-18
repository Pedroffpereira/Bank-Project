package com.example.Bank.Services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Service interface for generating authentication tokens.
 * 
 * <p>
 * This interface declares a method for generating a JSON Web Token (JWT) based
 * on the provided authentication.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define token generation operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public interface TokenService {
    /**
     * Generates a JSON Web Token (JWT) based on the provided authentication.
     * 
     * <p>
     * This method generates a JWT for the given authentication, which can be used
     * for user authentication and authorization.
     * </p>
     * 
     * @param auth The authentication object containing user authentication details.
     * @return A string representing the generated JWT.
     */
    public String generateJwt(Authentication auth);
}
