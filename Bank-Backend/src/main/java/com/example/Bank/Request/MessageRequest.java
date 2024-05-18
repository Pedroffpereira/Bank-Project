package com.example.Bank.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a message request.
 * This class encapsulates the necessary information for sending a message.
 * 
 * @author Pedro Pereira
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    /**
     * The text of the message.
     */
    String text;
}
