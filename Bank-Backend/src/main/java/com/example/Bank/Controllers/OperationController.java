package com.example.Bank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Request.WithdrawalDepositRequest;
import com.example.Bank.Response.TransactionResponse;

import jakarta.validation.Valid;

/**
 * Interface for handling banking operation-related endpoints.
 * 
 * This interface defines methods for handling endpoints related to banking
 * operations
 * such as deposit, withdrawal, and transfer in the application.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/operations")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public interface OperationController {
        /**
         * Handle deposit operation.
         * 
         * This method handles the endpoint for depositing funds into an account.
         * It takes authentication details and a request object containing
         * information about the deposit, and returns a ResponseEntity containing
         * a TransactionResponse with details of the deposit transaction.
         * 
         * @param jwt                      The JWT token containing authentication
         *                                 information.
         * @param withdrawalDepositRequest The request object containing information
         *                                 about the deposit.
         * @return A ResponseEntity containing a TransactionResponse with details of the
         *         deposit transaction.
         */
        public ResponseEntity<TransactionResponse> deposit(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody @Valid WithdrawalDepositRequest withdrawalDepositRequest);

        /**
         * Handle withdrawal operation.
         * 
         * This method handles the endpoint for withdrawing funds from an account.
         * It takes authentication details and a request object containing
         * information about the withdrawal, and returns a ResponseEntity containing
         * a TransactionResponse with details of the withdrawal transaction.
         * 
         * @param jwt                      The JWT token containing authentication
         *                                 information.
         * @param withdrawalDepositRequest The request object containing information
         *                                 about the withdrawal.
         * @return A ResponseEntity containing a TransactionResponse with details of the
         *         withdrawal transaction.
         */
        public ResponseEntity<TransactionResponse> withdrawal(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody @Valid WithdrawalDepositRequest withdrawalDepositRequest);

        /**
         * Handle transfer operation.
         * 
         * This method handles the endpoint for transferring funds between accounts.
         * It takes authentication details and a request object containing
         * information about the transfer, and returns a ResponseEntity containing
         * a TransactionResponse with details of the transfer transaction.
         * 
         * @param jwt             The JWT token containing authentication information.
         * @param transferRequest The request object containing information about the
         *                        transfer.
         * @return A ResponseEntity containing a TransactionResponse with details of the
         *         transfer transaction.
         */
        public ResponseEntity<TransactionResponse> transfer(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody @Valid TransferRequest transferRequest);
}
