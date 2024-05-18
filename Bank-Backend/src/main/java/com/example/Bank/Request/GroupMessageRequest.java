package com.example.Bank.Request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request to send a message to a group.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageRequest {
    /**
     * The ID of the group to send the message to.
     */
    UUID groupId;
}
