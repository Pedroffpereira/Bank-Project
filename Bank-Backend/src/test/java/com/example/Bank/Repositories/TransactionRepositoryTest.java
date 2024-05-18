package com.example.Bank.Repositories;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.Bank.Entaties.Transaction;

@SpringBootTest
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void testFindByProductCategoryIdAndOrderId_ReturnsOrdersItemList() {

        List<Transaction> transactions = new ArrayList<Transaction>();

        transactions.add(Transaction.builder()

                .build()
        );

        PageRequest pageRequest = PageRequest.of(0, 10);

        when(this.transactionRepository.findByAccount_AccountNumber(null, pageRequest)).thenReturn(transactions);

        assertThat(this.transactionRepository.findByAccount_AccountNumber(null, pageRequest)).isEqualTo(transactions);
    }

}
