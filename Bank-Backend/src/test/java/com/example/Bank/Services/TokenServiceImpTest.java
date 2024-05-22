package com.example.Bank.Services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bank.Services.Imp.TokenServiceImp;

@SpringBootTest
public class TokenServiceImpTest {
    @InjectMocks
    TokenServiceImp tokenServiceImp;

    @Test
    void generateJwt(){
        
    }
}
