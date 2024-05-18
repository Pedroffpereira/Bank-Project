package com.example.Bank.Services.Imp;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.Bank.Services.TokenService;

/**
 * Service implementation for generating JSON Web Tokens (JWTs).
 * 
 * <p>
 * This class implements the TokenService interface and provides a method to
 * generate JWTs based on
 * the provided authentication details.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to generate JWTs for user authentication.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class TokenServiceImp implements TokenService {
    /**
     * This field represents a JwtEncoder, which is used for encoding JWTs (JSON Web
     * Tokens).
     */
    private JwtEncoder jwtEncoder;

    /**
     * Constructs a new TokenServiceImp instance with the specified JwtEncoder.
     * 
     * @param jwtEncoder The JwtEncoder used to encode JWTs.
     */
    TokenServiceImp(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Generates a JSON Web Token (JWT) based on the provided authentication
     * details.
     * 
     * @param auth The authentication details.
     * @return A JWT representing the authenticated user.
     */
    @Override
    public String generateJwt(Authentication auth) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
