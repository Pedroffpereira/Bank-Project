package com.example.Bank.Services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.ChatGroup;
import com.example.Bank.Entaties.Message;
import com.example.Bank.Entaties.User;
import com.example.Bank.Mappers.Imp.ChatGroupMapper;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Repositories.ChatGroupRepository;
import com.example.Bank.Repositories.MessageRepository;
import com.example.Bank.Request.CreateChatGroup;
import com.example.Bank.Request.MessageRequest;
import com.example.Bank.Response.ChatGroupResponse;
import com.example.Bank.Services.Imp.ChatGroupServiceImp;

@SpringBootTest
public class ChatGroupServiceTest {

    @InjectMocks
    private ChatGroupServiceImp chatGroupService;

    @Mock
    private ChatGroupRepository chatGroupRepository;

    @Mock
    private ChatGroupMapper chatGroupMapper;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MessageRepository messageRepository;
    User user = User.builder().name("dqwd").build();
    Account account = Account.builder().user(user).accountNumber("1223").balance(12D).iban("fewfwe").build();

    ChatGroup chatGroup = ChatGroup.builder()
            .description(null)
            .accounts(Stream.of(account).collect(Collectors.toList()))
            .build();
    List<ChatGroup> chatGroups = new ArrayList<>();

    @Test
    void getChatGroup() {
        chatGroups.add(chatGroup);
        List<ChatGroupResponse> chatGroupResponses = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(0, 10);
        when(chatGroupRepository.findByAccounts_AccountNumber(
                "1223",
                pageRequest)).thenReturn(chatGroups);
        for (ChatGroup chatGroup : chatGroups) {
            ChatGroupMapper mapper = new ChatGroupMapper();
            ChatGroupResponse chatGroupResponse = mapper.toDTO(chatGroup);
            chatGroupResponses.add(chatGroupResponse);
            when(chatGroupMapper.toDTO(ArgumentMatchers.any())).thenReturn(chatGroupResponse);
        }
        assertThat(chatGroupService.getChatGroup("1223", 0, 10)).isEqualTo(chatGroupResponses);
    }

    @Test
    void showGroup() {
        chatGroup.setId(UUID.randomUUID());
        ChatGroupMapper mapper = new ChatGroupMapper();
        when(this.chatGroupRepository.findByIdAndAccounts_AccountNumber(chatGroup.getId(),
                "1223")).thenReturn(Optional.of(chatGroup));
        ChatGroupResponse chatGroupResponses = mapper.toDTO(chatGroup);
        when(chatGroupMapper.toDTO(ArgumentMatchers.any())).thenReturn(chatGroupResponses);
        assertThat(chatGroupService.showGroup("1223", chatGroup.getId())).isEqualTo(chatGroupResponses);

    }

    @Test
    void createGroup() {
        List<String> ibans = new ArrayList<>();
        ibans.add("dwdqwdwqd");
        CreateChatGroup createChatGroup = CreateChatGroup.builder().description("fdwef").ibans(ibans).build();
        when(accountRepository.findByAccountNumber(ArgumentMatchers.any())).thenReturn(Optional.of(account));
        when(this.accountRepository.findByIban(ArgumentMatchers.any())).thenReturn(Optional.of(account));
        ChatGroupMapper mapper = new ChatGroupMapper();

        ChatGroupResponse chatGroupResponses = mapper.toDTO(chatGroup);
        when(chatGroupMapper.toDTO(ArgumentMatchers.any())).thenReturn(chatGroupResponses);
        assertThat(chatGroupService.createGroup("1223", createChatGroup)).isEqualTo(chatGroupResponses);

    }

    @Test
    void addMessage() {
        chatGroup.setId(UUID.randomUUID());
        MessageRequest messageRequest = MessageRequest.builder().text("dwdwd").build();
        Message message = Message.builder().text("dwdwd").chatGroup(chatGroup).account(account).build();
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        chatGroup.setMessages(messages);
        ChatGroupMapper mapper = new ChatGroupMapper();

        when(this.accountRepository.findByAccountNumber("1223")).thenReturn(Optional.of(account));

        when(this.chatGroupRepository.findByIdAndAccounts_AccountNumber(chatGroup.getId(),
                "1223")).thenReturn(Optional.of(chatGroup));

        ChatGroupResponse chatGroupResponses = mapper.toDTO(chatGroup);

        when(chatGroupMapper.toDTO(ArgumentMatchers.any())).thenReturn(chatGroupResponses);
        when(messageRepository.save(message)).thenReturn(message);

        assertThat(chatGroupService.addMessage("1223", messageRequest, chatGroup.getId()))
                .isEqualTo((chatGroupResponses));
    }
}
