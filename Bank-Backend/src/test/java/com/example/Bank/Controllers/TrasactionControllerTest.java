package com.example.Bank.Controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TrasactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    
    @Test
    @WithMockUser(username = "1234567", password = "ree")
    void 
}
