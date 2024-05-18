package com.example.Bank.Services.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.ChatGroup;
import com.example.Bank.Entaties.Message;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Mappers.Imp.ChatGroupMapper;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Repositories.ChatGroupRepository;
import com.example.Bank.Repositories.MessageRepository;
import com.example.Bank.Request.CreateChatGroup;
import com.example.Bank.Request.MessageRequest;
import com.example.Bank.Response.ChatGroupResponse;
import com.example.Bank.Services.ChatGroupService;

/**
 * Service implementation for managing chat groups.
 * 
 * <p>
 * This class implements the ChatGroupService interface and provides methods for
 * interacting with chat groups,
 * such as retrieving, creating, and adding messages to chat groups.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to manage chat groups and handle operations
 * related to chat groups.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class ChatGroupServiceImp implements ChatGroupService {
    /**
     * The repository for accessing account data.
     */
    final AccountRepository accountRepository;

    /**
     * The repository for accessing chat group data.
     */
    final ChatGroupRepository chatGroupRepository;

    /**
     * The repository for accessing message data.
     */
    final MessageRepository messageRepository;

    /**
     * The mapper for converting between ChatGroup entities and ChatGroupResponse
     * DTOs.
     */
    final DTOMapper<ChatGroup, ChatGroupResponse> chatGroupMapper;

    /**
     * Constructs a new ChatGroupServiceImp instance with the specified
     * repositories.
     * 
     * @param accountRepository   The repository for accessing account data.
     * 
     * @param chatGroupRepository The repository for accessing chat group data.
     * @param messageRepository   The repository for accessing message data.
     * @param chatGroupMapper     The Mapper for convert entatie to DTO.
     */
    ChatGroupServiceImp(AccountRepository accountRepository, ChatGroupRepository chatGroupRepository,
            MessageRepository messageRepository, ChatGroupMapper chatGroupMapper) {
        this.accountRepository = accountRepository;
        this.chatGroupRepository = chatGroupRepository;
        this.messageRepository = messageRepository;
        this.chatGroupMapper = chatGroupMapper;
    }

    /**
     * Retrieves a list of chat groups associated with the specified contract
     * number.
     * 
     * @param contractNumber The contract number associated with the user.
     * @param page           The page number of results to retrieve.
     * @param size           The number of results per page.
     * @return A list of chat group responses.
     * @throws IllegalAccessError if the chat groups do not exist.
     */
    @Override
    public List<ChatGroupResponse> getChatGroup(String contractNumber, Integer page, Integer size) {
        Optional<List<ChatGroup>> optionalChatGroup = this.chatGroupRepository.findByAccounts_AccountNumber(
                contractNumber,
                PageRequest.of(page, size));

        if (optionalChatGroup.isEmpty()) {
            throw new IllegalAccessError("Grupo não existe");
        }
        List<ChatGroupResponse> ChatGroupResponseList = new ArrayList<ChatGroupResponse>();
        for (ChatGroup chatGroup : optionalChatGroup.get()) {
            ChatGroupResponseList.add(chatGroupMapper.toDTO(chatGroup));
        }

        return ChatGroupResponseList;
    }

    /**
     * Retrieves the details of a specific chat group.
     * 
     * @param contractNumber The contract number associated with the user.
     * @param groupId        The ID of the chat group to retrieve.
     * @return The chat group response.
     * @throws IllegalAccessError if the chat group does not exist.
     */
    @Override
    public ChatGroupResponse showGroup(String contractNumber, UUID groupId) {
        Optional<ChatGroup> optionalChatGroup = this.chatGroupRepository.findByIdAndAccounts_AccountNumber(groupId,
                contractNumber);
        if (optionalChatGroup.isEmpty()) {
            throw new IllegalAccessError("Grupo não existe");
        }
        return chatGroupMapper.toDTO(optionalChatGroup.get());
    }

    /**
     * Creates a new chat group with the specified description and members.
     * 
     * @param contractNumber  The contract number associated with the user creating
     *                        the chat group.
     * @param createChatGroup The details of the chat group to create.
     * @return The created chat group response.
     * @throws IllegalAccessError if any of the specified IBANs do not exist.
     */
    @Override
    public ChatGroupResponse createGroup(String contractNumber, CreateChatGroup createChatGroup) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(contractNumber);
        List<Account> accounts = new ArrayList<Account>();
        for (String iban : createChatGroup.getIbans()) {
            Optional<Account> outherAccount = this.accountRepository.findByIban(iban);
            if (outherAccount.isEmpty()) {
                throw new IllegalAccessError("iban não existe");
            }
            accounts.add(outherAccount.get());
        }
        accounts.add(accountOptional.get());
        return chatGroupMapper.toDTO(chatGroupRepository.save(ChatGroup.builder()
                .description(createChatGroup.getDescription())
                .accounts(accounts)
                .build()));

    }

    /**
     * Adds a message to the specified chat group.
     * 
     * @param contractNumber The contract number associated with the user adding the
     *                       message.
     * @param messageRequest The message to add to the chat group.
     * @param groupId        The ID of the chat group to which the message will be
     *                       added.
     * @return The updated chat group response.
     * @throws IllegalAccessError if the chat group does not exist.
     */
    @Override
    public ChatGroupResponse addMessage(String contractNumber, MessageRequest messageRequest, UUID groupId) {
        Optional<ChatGroup> optionalChatGroup = this.chatGroupRepository.findByIdAndAccounts_AccountNumber(groupId,
                contractNumber);
        Optional<Account> optionalAccount = this.accountRepository.findByAccountNumber(contractNumber);

        Account account = optionalAccount.get();
        if (optionalChatGroup.isEmpty()) {
            throw new IllegalAccessError("Grupo não existe");
        }
        ChatGroup chatGroup = optionalChatGroup.get();
        List<Message> messages = chatGroup.getMessages();
        Message message = Message.builder()
                .account(account)
                .chatGroup(chatGroup)
                .text(messageRequest.getText())
                .build();
        messages.add(messageRepository.save(message));

        chatGroup.setMessages(messages);

        return chatGroupMapper.toDTO(this.chatGroupRepository.save(chatGroup));
    }
}
