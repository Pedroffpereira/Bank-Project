package com.example.Bank.Repositories;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.ChatGroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ChatGroupRepositoryTest {
        @Mock
        ChatGroupRepository chatGroupRepository;
        Account account = Account.builder()
                        .accountNumber("902131232")
                        .balance(12D).iban("ptcdscsdcwec")
                        .build();
        ChatGroup chatGroup = ChatGroup.builder()
                        .accounts(Stream.of(account).collect(Collectors.toList()))
                        .build();

        @Test
        void findByIdAndAccounts_AccountNumber() {
                UUID uuid = UUID.randomUUID();
                when(chatGroupRepository.findByIdAndAccounts_AccountNumber(uuid, "902131232"))
                                .thenReturn(Optional.of(chatGroup));

                assertThat(chatGroupRepository.findByIdAndAccounts_AccountNumber(uuid, "902131232"))
                                .isEqualTo(Optional.of(chatGroup));
        }

        @Test
        void findByAccounts_id() {

                when(chatGroupRepository.findByAccounts_id(ArgumentMatchers.any()))
                                .thenReturn(Stream.of(chatGroup).collect(Collectors.toList()));

                assertThat(chatGroupRepository.findByAccounts_id(ArgumentMatchers.any()))
                                .isEqualTo(Stream.of(chatGroup).collect(Collectors.toList()));
        }

        @Test
        void findByAccounts_AccountNumber() {

                when(chatGroupRepository.findByAccounts_AccountNumber("902131232", PageRequest.of(0, 10)))
                                .thenReturn(Stream.of(chatGroup).collect(Collectors.toList()));

                assertThat(chatGroupRepository.findByAccounts_AccountNumber("902131232", PageRequest.of(0, 10)))
                                .isEqualTo(Stream.of(chatGroup).collect(Collectors.toList()));
        }
}
