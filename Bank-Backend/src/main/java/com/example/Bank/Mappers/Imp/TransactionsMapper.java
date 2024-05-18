package com.example.Bank.Mappers.Imp;

import org.springframework.stereotype.Component;

import com.example.Bank.Entaties.Transaction;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Response.TransactionResponse;

/**
 * Mapper implementation for converting between Transaction entities and
 * TransactionResponse DTOs.
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Component
public class TransactionsMapper implements DTOMapper<Transaction, TransactionResponse> {
    /**
     * Converts a TransactionResponse DTO to a Transaction entity.
     *
     * @param transactionResponse The TransactionResponse DTO to be converted.
     * @return The corresponding Transaction entity.
     */
    public Transaction toEntatiy(TransactionResponse transactionResponse) {
        return Transaction.builder()
                .build();
    }

    /**
     * Converts a Transaction entity to a TransactionResponse DTO.
     *
     * @param transaction The Transaction entity to be converted.
     * @return The corresponding TransactionResponse DTO.
     */
    public TransactionResponse toDTO(Transaction transaction) {
        return TransactionResponse.builder()
                .description(transaction.getDescription()).amount(transaction.getAmount())
                .balance_after_transaction(transaction.getBalance_after_transaction())
                .iban(transaction.getIban())
                .build();

    }
}
