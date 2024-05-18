package com.example.Bank.Mappers.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.ChatGroup;
import com.example.Bank.Entaties.Message;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Response.ChatGroupResponse;
import com.example.Bank.Response.MessageResponse;

/**
 * Mapper implementation for converting between ChatGroup entities and
 * ChatGroupResponse DTOs.
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Component
public class ChatGroupMapper implements DTOMapper<ChatGroup, ChatGroupResponse> {
    /**
     * Converts a ChatGroupResponse DTO to a ChatGroup entity.
     *
     * @param chatGroupResponse The ChatGroupResponse DTO to be converted.
     * @return The corresponding ChatGroup entity.
     */
    public ChatGroup toEntatiy(ChatGroupResponse chatGroupResponse) {
        return ChatGroup.builder()
                .description(chatGroupResponse.getDescription())
                .build();
    }

    /**
     * Converts a ChatGroup entity to a ChatGroupResponse DTO.
     *
     * @param chatGroup The ChatGroup entity to be converted.
     * @return The corresponding ChatGroupResponse DTO.
     */
    public ChatGroupResponse toDTO(ChatGroup chatGroup) {
        DTOMapper<Message, MessageResponse> messageMapper = new MessageMapper();
        List<MessageResponse> messageReponseList = new ArrayList<MessageResponse>();
        List<String> ibans = new ArrayList<String>();
        System.out.println("antes");
        if (chatGroup.getMessages() != null) {
            for (Message message : chatGroup.getMessages()) {
                messageReponseList.add(messageMapper.toDTO(message));
            }
        }
        for (Account account : chatGroup.getAccounts()) {
            ibans.add(account.getIban());
        }

        return ChatGroupResponse.builder()
                .id(chatGroup.getId())
                .description(chatGroup.getDescription())
                .messages(messageReponseList)
                .ibans(ibans)
                .build();
    }
}
