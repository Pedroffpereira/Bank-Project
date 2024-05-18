package com.example.Bank.Controllers.Imp;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Controllers.TrasactionController;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.TrasactionService;
import com.example.Bank.Services.Imp.TrasactionServiceImp;

/**
 * Implementation of the TrasactionController interface for handling
 * transaction-related endpoints.
 * 
 * This class implements the TrasactionController interface and provides
 * implementations for handling transaction-related endpoints such as
 * retrieving transactions in the application.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/trasaction")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class TrasactionControllerImp implements TrasactionController {
    /**
     * The TrasactionService for handling transactions.
     */
    TrasactionService trasactionService;

    /**
     * Constructor for the TrasactionControllerImp class.
     * 
     * Initializes the TrasactionControllerImp with the provided TrasactionService.
     * 
     * @param trasactionService The TrasactionService to use for transaction
     *                          handling.
     */
    public TrasactionControllerImp(TrasactionServiceImp trasactionService) {
        this.trasactionService = trasactionService;
    }

    /**
     * Retrieve transactions.
     * 
     * This method handles the endpoint to retrieve transactions.
     * It takes authentication details, pagination parameters, and returns
     * a list of TransactionResponse objects representing the transactions.
     * 
     * @param jwt  The JWT token containing authentication information.
     * @param page The page number for pagination (default: 0).
     * @param size The page size for pagination (default: 10).
     * @return A list of TransactionResponse objects representing the transactions.
     */
    @Override
    @GetMapping()
    public List<TransactionResponse> getTrasaction(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return this.trasactionService.getTransaction(jwt.getSubject(), page, size);
    }
}
