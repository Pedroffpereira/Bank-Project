package com.example.Bank.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Request.WithdrawalDepositRequest;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.Imp.OperationsServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class OperationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @MockBean
    OperationsServiceImp operationsService;

    @MockBean
    AccountRepository accountRepository;

    @Test
    @WithMockUser(username = "1234567", password = "ree")
    void depositTest() throws Exception {

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .description("Teste")
                .amount(12D)
                .balance_after_transaction(88D)
                .iban("PT50000101233064223714054")
                .build();
        when(operationsService.withdrawal(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(transactionResponse);

        ObjectMapper objectMapper = mapperBuilder.build();
        ResultActions response = mvc
                .perform(post("/api/v1/operations/deposit").with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(WithdrawalDepositRequest.builder().ammount(12D).build())));

        response.andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "1234567", password = "ree")
    void transferTest() throws Exception {

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .description("Teste")
                .amount(12D)
                .balance_after_transaction(88D)
                .iban("PT50000101233064223714054")
                .build();
        // Account account = Account.builder().build();
        // Optional<Account> optional = Optional.of(account);
        // when(this.accountRepository.findByAccountNumber("")).thenReturn(optional);
        when(operationsService.withdrawal(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(transactionResponse);

        ObjectMapper objectMapper = mapperBuilder.build();
        ResultActions response = mvc
                .perform(post("/api/v1/operations/transfer").with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(WithdrawalDepositRequest.builder().ammount(12D).build())));

        response.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "1234567", password = "ree")
    void withdrawal() throws Exception {

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .description("Teste")
                .amount(12D)
                .balance_after_transaction(88D)
                .iban("PT50000101233064223714054")
                .build();
        // Account account = Account.builder().build();
        // Optional<Account> optional = Optional.of(account);
        // when(this.accountRepository.findByAccountNumber("")).thenReturn(optional);
        when(operationsService.withdrawal(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(transactionResponse);

        ObjectMapper objectMapper = mapperBuilder.build();
        ResultActions response = mvc
                .perform(post("/api/v1/operations/withdrawal").with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(WithdrawalDepositRequest.builder().ammount(12D).build())));

        response.andExpect(status().isOk());
    }
}
