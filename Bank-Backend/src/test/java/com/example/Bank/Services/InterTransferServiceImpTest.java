package com.example.Bank.Services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Request.TransferRequest;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.Imp.InterTransferServiceImp;
import com.example.Bank.Services.Imp.TrasactionServiceImp;

@SpringBootTest
public class InterTransferServiceImpTest {
    @InjectMocks
    InterTransferServiceImp interTransferServiceImp;
    @Mock
    AccountRepository accountRepository;
    @Mock
    TrasactionServiceImp trasactionService;

    Account account = Account.builder().balance(2D).iban("ghtfegrergreg").build();
    Account outherAccount = Account.builder().balance(2D).iban("dwqdqwd").build();

    @Test
    void Transfer() {

        when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));
        TransactionResponse transactionResponse = TransactionResponse.builder().build();
        when(accountRepository.findByIban(outherAccount.getIban())).thenReturn(Optional.of(account));
        TransferRequest transferRequest = TransferRequest.builder().ammount(1D).iban(outherAccount.getIban()).build();
        when(trasactionService.insertTransfer(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(transactionResponse);

        assertThat(interTransferServiceImp.Transfer(account.getAccountNumber(), transferRequest))
                .isEqualTo(transactionResponse);
    }
}
