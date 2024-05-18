package com.example.Bank.Response;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class representing a response for a chat group.
 * 
 * This class provides information about a chat group, including its unique
 * identifier,
 * description, messages, and associated IBANs.
 * 
 * @author Pedro Pereira
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatGroupResponse {
    /**
     * Unique identifier of the chat group.
     * 
     * This field stores the unique identifier (UUID) of the chat group.
     */
    UUID id;
    /**
     * Description or name of the chat group.
     * 
     * This field holds the description or name of the chat group.
     */
    String description;
    /**
     * List of messages within the chat group.
     * 
     * This field contains a list of message data transfer objects (MessageResponse)
     * representing the messages exchanged within the chat group.
     */
    List<MessageResponse> messages;
    /**
     * List of IBANs associated with the chat group.
     * 
     * This field stores a list of International Bank Account Numbers (IBANs)
     * associated with the chat group.
     */
    List<String> ibans;
}
