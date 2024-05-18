package com.example.Bank.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a response containing a JWT
 * token.
 * This class encapsulates the necessary information for responding with a JWT
 * token.
 * 
 * @author Pedro Pereira
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JWTResponse {
    /**
     * The JWT token.
     */
    String token;
}
