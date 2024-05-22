package com.example.Bank.Services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Bank.Services.Imp.AuthenticationServiceImp;

@SpringBootTest
public class AuthenticationServiceTest {
    @Mock
    private AuthenticationServiceImp authenticationServiceImp;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void loginUser() {
        when(authenticationManager.authenticate(ArgumentMatchers.any())).thenReturn(null);
    }

    @Test
    void RegistrationAccount() {

    }

    @Test
    void auth() {

    }
}
