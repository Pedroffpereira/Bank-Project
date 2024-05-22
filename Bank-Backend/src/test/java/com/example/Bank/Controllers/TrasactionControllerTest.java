package com.example.Bank.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
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

import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.Imp.TrasactionServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TrasactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @MockBean
    private TrasactionServiceImp trasactionService;

    @Test
    @WithMockUser(username = "1234567", password = "ree")
    void getTrasactionTest() throws Exception {
        TransactionResponse transactionResponse = TransactionResponse.builder()
                .description("Teste")
                .amount(12D)
                .balance_after_transaction(88D)
                .iban("PT50000101233064223714054")
                .build();

        List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(transactionResponse);

        ObjectMapper objectMapper = mapperBuilder.build();

        when(trasactionService.getTransaction(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(transactionResponseList);

        ResultActions response = mvc.perform(get("/api/v1/trasaction").with(SecurityMockMvcRequestPostProcessors.jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)));

        response.andExpect(status().isOk());
    }
}
