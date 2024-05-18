package com.example.Bank.Controllers;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Response.TransactionResponse;

/**
 * Interface for handling transaction-related endpoints.
 * 
 * This interface defines a method for handling the endpoint to retrieve
 * transactions
 * in the application.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/trasaction")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public interface TrasactionController {
    /**
     * Get a list of transactions.
     * 
     * This method handles the endpoint to retrieve a list of transactions.
     * It takes authentication details, pagination parameters, and returns
     * a list of TransactionResponse objects representing the transactions.
     * 
     * @param jwt  The JWT token containing authentication information.
     * @param page The page number for pagination (default: 0).
     * @param size The page size for pagination (default: 10).
     * @return A list of TransactionResponse objects representing the transactions.
     */
    public List<TransactionResponse> getTrasaction(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size);
}
