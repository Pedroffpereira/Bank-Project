package com.example.Bank.Request;

import org.springframework.security.oauth2.jwt.Jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a request to get all chat groups.
 * This class encapsulates the necessary information for retrieving all chat
 * groups.
 * 
 * @author Pedro Pereira
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllChatGroupsRequest {
    /**
     * The JWT token used for authentication.
     */
    Jwt jwt;
    /**
     * The page number for pagination.
     */
    Integer page;
    /**
     * The size of each page for pagination.
     */
    Integer size;
}
