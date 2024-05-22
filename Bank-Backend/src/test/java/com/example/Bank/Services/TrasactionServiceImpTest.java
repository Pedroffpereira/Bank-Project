package com.example.Bank.Services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.Transaction;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Mappers.Imp.TransactionsMapper;
import com.example.Bank.Repositories.TransactionRepository;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.Imp.TrasactionServiceImp;

@SpringBootTest
public class TrasactionServiceImpTest {
    @InjectMocks
    TrasactionServiceImp trasactionServiceImp;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    TransactionsMapper transactionsMapper;
    Transaction transaction = Transaction.builder()
            .amount(10D)
            .balance_after_transaction(12D)
            .account(Account.builder().accountNumber("9090902132").build())
            .build();

    @Test
    void getTransactionTest() {

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(this.transaction);

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<TransactionResponse> transactionResponsesList = new ArrayList<>();
        when(transactionRepository.findByAccount_AccountNumber("9090902132", pageRequest)).thenReturn(transactions);
        for (Transaction transaction : transactions) {
            TransactionsMapper tMapper = new TransactionsMapper();
            TransactionResponse transactionResponse = tMapper.toDTO(transaction);
            transactionResponsesList.add(transactionResponse);
            when(transactionsMapper.toDTO(transaction)).thenReturn(transactionResponse);
        }
        assertThat(trasactionServiceImp.getTransaction("9090902132", 0, 10)).isEqualTo(transactionResponsesList);
    }

    @Test
    void insertTransfer() {
        when(this.transactionRepository.save(transaction)).thenReturn(transaction);
        TransactionsMapper tMapper = new TransactionsMapper();
        when(transactionsMapper.toDTO(ArgumentMatchers.any())).thenReturn(tMapper.toDTO(transaction));

        assertThat(trasactionServiceImp.insertTransfer(transaction.getAccount(), transaction.getAmount(), null))
                .isEqualTo(tMapper.toDTO(transaction));
    }

    @Test
    void InsertWithdrawal() {
        when(this.transactionRepository.save(transaction)).thenReturn(transaction);
        TransactionsMapper tMapper = new TransactionsMapper();
        when(transactionsMapper.toDTO(ArgumentMatchers.any())).thenReturn(tMapper.toDTO(transaction));

        assertThat(trasactionServiceImp.InsertDeposit(transaction.getAccount(), transaction.getAmount()))
                .isEqualTo(tMapper.toDTO(transaction));
    }

    @Test
    void InsertDeposit() {

        when(this.transactionRepository.save(transaction)).thenReturn(transaction);
        TransactionsMapper tMapper = new TransactionsMapper();
        when(transactionsMapper.toDTO(ArgumentMatchers.any())).thenReturn(tMapper.toDTO(transaction));

        assertThat(trasactionServiceImp.InsertDeposit(transaction.getAccount(), transaction.getAmount()))
                .isEqualTo(tMapper.toDTO(transaction));
    }

}