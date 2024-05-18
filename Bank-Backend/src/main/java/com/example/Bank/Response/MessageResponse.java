package com.example.Bank.Response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a message response.
 * This class encapsulates the necessary information for responding with a
 * message.
 * 
 * @author [Your Name]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    /**
     * The text of the message.
     */
    String text;
    /**
     * The sender of the message.
     */
    String from;
    /**
     * The IBAN (International Bank Account Number) associated with the sender.
     */
    String iban;
    /**
     * The UUID of the group to which the message belongs.
     */
    UUID groupId;
}
