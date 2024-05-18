package com.example.Bank.Mappers.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.Transaction;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Response.AccoutResponse;
import com.example.Bank.Response.TransactionResponse;

/**
 * Mapper implementation for converting between Account entities and
 * AccoutResponse DTOs.
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Component
public class AccountMapper implements DTOMapper<Account, AccoutResponse> {
    /**
     * Converts an AccoutResponse DTO to an Account entity.
     *
     * @param accoutResponse The AccoutResponse DTO to be converted.
     * @return The corresponding Account entity.
     */
    public Account toEntatiy(AccoutResponse accoutResponse) {
        return Account.builder()
                .build();
    }

    /**
     * Converts an Account entity to an AccoutResponse DTO.
     *
     * @param account The Account entity to be converted.
     * @return The corresponding AccoutResponse DTO.
     */
    public AccoutResponse toDTO(Account account) {
        DTOMapper<Transaction, TransactionResponse> transactionsMapper = new TransactionsMapper();
        List<TransactionResponse> transactionResponseList = new ArrayList<TransactionResponse>();
        for (Transaction transaction : account.getTransactions()) {
            TransactionResponse transactionResponse = transactionsMapper.toDTO(transaction);
            transactionResponseList.add(transactionResponse);
        }
        return AccoutResponse.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .iban(account.getIban())
                .transactions(transactionResponseList)
                .build();
    }
}
