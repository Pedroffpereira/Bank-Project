package com.example.Bank.Services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bank.Configurations.BankSettings;
import com.example.Bank.Entaties.Account;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Request.WithdrawalDepositRequest;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.Imp.OperationsServiceImp;
import com.example.Bank.Services.Imp.TrasactionServiceImp;

@SpringBootTest
public class OperationsServiceImpTest {

    @InjectMocks
    OperationsServiceImp operationsServiceImp;

    @Mock
    AccountRepository accountRepository;

    @Mock
    TrasactionServiceImp trasactionService;

    @Mock
    BankSettings bankSettings;

    Account account = Account.builder()
            .accountNumber("dwdwqd")
            .iban("qdwefqwdqwd")
            .balance(3D)
            .build();

    @Test
    void deposit() {
        WithdrawalDepositRequest withdrawalDepositRequest = WithdrawalDepositRequest.builder().ammount(4D).build();

        TransactionResponse transactionResponse = TransactionResponse.builder().build();

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));

        when(trasactionService.InsertDeposit(account, withdrawalDepositRequest.getAmmount()))
                .thenReturn(transactionResponse);

        assertThat(operationsServiceImp.deposit(account.getAccountNumber(), withdrawalDepositRequest))
                .isEqualTo(transactionResponse);
    }

    @Test
    void withdrawal() {
        WithdrawalDepositRequest withdrawalDepositRequest = WithdrawalDepositRequest.builder().ammount(2D).build();

        TransactionResponse transactionResponse = TransactionResponse.builder().build();

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));

        when(trasactionService.InsertWithdrawal(account, withdrawalDepositRequest.getAmmount()))
                .thenReturn(transactionResponse);

        assertThat(operationsServiceImp.withdrawal(account.getAccountNumber(), withdrawalDepositRequest))
                .isEqualTo(transactionResponse);
    }

    @Test
    void Transfer() {
        TransferRequest transferRequest = TransferRequest.builder()
                .ammount(1D)
                .iban("PT500001").build();

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .amount(1D)
                .balance_after_transaction(2D)
                .description("wdqwd")
                .iban("wdwqdwqd")
                .build();

        assertThat(operationsServiceImp.Transfer(account.getAccountNumber(), transferRequest))
                .isEqualTo(transactionResponse);
    }
}
