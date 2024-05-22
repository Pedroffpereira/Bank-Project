package com.example.Bank.Controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Bank.Services.Imp.ChatGroupServiceImp;
import com.example.Bank.Services.Imp.OperationsServiceImp;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ChatGroupsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @MockBean
    ChatGroupServiceImp chatGroupServiceImp;

    @Test
    void getChatGroups() {
        when(chatGroupServiceImp.getChatGroup(ArgumentMatchers.any(String.class), ArgumentMatchers.any(),
                ArgumentMatchers.any()));

    }

    @Test
    void getChatGroup() {

    }

    @Test
    void createChatGroup() {

    }

    @Test
    void AddMessageToChatGroup() {

    }
}
