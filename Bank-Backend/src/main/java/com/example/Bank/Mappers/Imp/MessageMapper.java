package com.example.Bank.Mappers.Imp;

import org.springframework.stereotype.Component;

import com.example.Bank.Entaties.Message;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Response.MessageResponse;

/**
 * Mapper implementation for converting between Message entities and
 * MessageResponse DTOs.
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Component
public class MessageMapper implements DTOMapper<Message, MessageResponse> {
    /**
     * Converts a MessageResponse DTO to a Message entity.
     *
     * @param messageResponse The MessageResponse DTO to be converted.
     * @return The corresponding Message entity.
     */
    public Message toEntatiy(MessageResponse messageResponse) {
        return Message.builder()
                .text(messageResponse.getText())
                .build();
    }

    /**
     * Converts a Message entity to a MessageResponse DTO.
     *
     * @param message The Message entity to be converted.
     * @return The corresponding MessageResponse DTO.
     */
    public MessageResponse toDTO(Message message) {
        return MessageResponse.builder()
                .text(message.getText())
                .groupId(message.getChatGroup().getId())
                .from(message.getAccount().getUser().getName())
                .iban(message.getAccount().getIban())
                .build();
    }
}
