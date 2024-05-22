package com.example.Bank.Repositories;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bank.Entaties.Account;

@SpringBootTest
public class AccountRepositoryTest {
    @Mock
    AccountRepository accountRepository;
    Account account = Account.builder()
            .accountNumber("902131232")
            .balance(12D).iban("ptcdscsdcwec")
            .build();

    @Test
    void findByAccountNumber() {

        when(this.accountRepository.findByAccountNumber("902131232")).thenReturn(Optional.of(account));

        assertThat(this.accountRepository.findByAccountNumber("902131232")).isEqualTo(Optional.of(account));

    }

    @Test
    void findByIban() {

        when(this.accountRepository.findByIban("ptcdscsdcwec")).thenReturn(Optional.of(account));

        assertThat(this.accountRepository.findByIban("ptcdscsdcwec")).isEqualTo(Optional.of(account));
    }
}
