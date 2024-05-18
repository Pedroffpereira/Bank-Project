package com.example.Bank.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.Bank.Request.ContractAndPasswordRequest;
import com.example.Bank.Request.CreateAccount;
import com.example.Bank.Response.CreateAccountResponse;
import com.example.Bank.Response.JWTResponse;
import com.example.Bank.Services.Imp.AuthenticationServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;
    @MockBean
    private AuthenticationServiceImp authenticationService;

    @Test
    public void registationUser() throws Exception {

        CreateAccount createAccount = CreateAccount.builder()
                .email("pedro@gmail.com")
                .password("password1123")
                .name("Pedro")
                .build();

        CreateAccountResponse createAccountResponse = CreateAccountResponse.builder()
                .iban("PT50000101233064223714054")
                .contractNumber("30642237140")
                .build();

        when(authenticationService.RegistrationAccount(ArgumentMatchers.any(CreateAccount.class)))
                .thenReturn(createAccountResponse);
        ObjectMapper objectMapper = mapperBuilder.build();
        ResultActions response = mvc.perform(post("/api/v1/users/registation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccount)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.iban").value("PT50000101233064223714054"));

    }

    @Test
    public void login() throws Exception {

        ContractAndPasswordRequest contractAndPasswordRequest = ContractAndPasswordRequest.builder()
                .contract("1234567")
                .password("ree")
                .build();
        JWTResponse jwtResponse = JWTResponse.builder().token("vredvervrr").build();
        when(authenticationService.loginUser(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class)))
                .thenReturn(JWTResponse.builder().token("vredvervrr").build());

        ObjectMapper objectMapper = mapperBuilder.build();

        ResultActions response = mvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contractAndPasswordRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("vredvervrr"));

    }

    @Test
    @WithMockUser(username = "1234567", password = "ree")
    public void auth() throws Exception {

        ObjectMapper objectMapper = mapperBuilder.build();
        ResultActions response = mvc.perform(post("/api/v1/users/auth").with(SecurityMockMvcRequestPostProcessors.jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)));

        response.andExpect(status().isOk());

    }
}
