package com.example.Bank.Controllers.Imp;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Controllers.OperationController;
import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Request.WithdrawalDepositRequest;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.OperationsService;
import com.example.Bank.Services.Imp.OperationsServiceImp;

import jakarta.validation.Valid;

/**
 * Implementation of the OperationController interface for handling banking
 * operation-related endpoints.
 * 
 * This class implements the OperationController interface and provides
 * implementations for handling banking operation-related endpoints such as
 * deposit, withdrawal, and transfer in the application.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/operations")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class OperationControllerImp implements OperationController {
    /**
     * The OperationsService for handling account operations.
     */
    final OperationsService operationsService;

    /**
     * Constructor for the OperationControllerImp class.
     * 
     * Initializes the OperationControllerImp with the provided OperationsService.
     * 
     * @param operationsService The OperationsService to use for banking operation
     *                          handling.
     */
    OperationControllerImp(OperationsServiceImp operationsService) {
        this.operationsService = operationsService;
    }

    /**
     * Handle deposit operation.
     * 
     * This method handles the deposit endpoint. It takes authentication details
     * and a request object containing information about the deposit, and returns
     * a ResponseEntity containing a TransactionResponse with details of the deposit
     * transaction.
     * 
     * @param jwt                      The JWT token containing authentication
     *                                 information.
     * @param withdrawalDepositRequest The request object containing information
     *                                 about the deposit.
     * @return A ResponseEntity containing a TransactionResponse with details of the
     *         deposit transaction.
     */
    @Override
    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid WithdrawalDepositRequest withdrawalDepositRequest) {
        return ResponseEntity.ok(operationsService.deposit(jwt.getSubject(), withdrawalDepositRequest));
    }

    /**
     * Handle withdrawal operation.
     * 
     * This method handles the withdrawal endpoint. It takes authentication details
     * and a request object containing information about the withdrawal, and returns
     * a ResponseEntity containing a TransactionResponse with details of the
     * withdrawal transaction.
     * 
     * @param jwt                      The JWT token containing authentication
     *                                 information.
     * @param withdrawalDepositRequest The request object containing information
     *                                 about the withdrawal.
     * @return A ResponseEntity containing a TransactionResponse with details of the
     *         withdrawal transaction.
     */
    @Override
    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionResponse> withdrawal(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid WithdrawalDepositRequest withdrawalDepositRequest) {
        return ResponseEntity.ok(operationsService.withdrawal(jwt.getSubject(), withdrawalDepositRequest));
    }

    /**
     * Handle transfer operation.
     * 
     * This method handles the transfer endpoint. It takes authentication details
     * and a request object containing information about the transfer, and returns
     * a ResponseEntity containing a TransactionResponse with details of the
     * transfer transaction.
     * 
     * @param jwt             The JWT token containing authentication information.
     * @param transferRequest The request object containing information about the
     *                        transfer.
     * @return A ResponseEntity containing a TransactionResponse with details of the
     *         transfer transaction.
     */
    @Override
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid TransferRequest transferRequest) {
        return ResponseEntity.ok(operationsService.Transfer(jwt.getSubject(), transferRequest));
    }
}
