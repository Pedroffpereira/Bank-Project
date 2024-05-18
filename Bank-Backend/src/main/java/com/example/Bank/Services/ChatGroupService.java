package com.example.Bank.Services;

import java.util.List;
import java.util.UUID;

import com.example.Bank.Request.CreateChatGroup;
import com.example.Bank.Request.MessageRequest;
import com.example.Bank.Response.ChatGroupResponse;

/**
 * Service interface for managing chat groups.
 * 
 * <p>
 * This interface declares methods for retrieving chat groups, showing specific
 * groups,
 * creating new groups, and adding messages to groups.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define chat group management operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
public interface ChatGroupService {
    /**
     * Retrieves a list of chat groups associated with the specified contract
     * number.
     * 
     * @param contractNumber The contract number associated with the user.
     * @param page           The page number of the result set to retrieve.
     * @param size           The size of each page in the result set.
     * @return A list of ChatGroupResponse objects representing the chat groups.
     */
    public List<ChatGroupResponse> getChatGroup(String contractNumber, Integer page, Integer size);

    /**
     * Shows the details of a specific chat group.
     * 
     * @param contractNumber The contract number associated with the user.
     * @param groupId        The unique identifier of the chat group to show.
     * @return A ChatGroupResponse object representing the details of the chat
     *         group.
     */
    public ChatGroupResponse showGroup(String contractNumber, UUID groupId);

    /**
     * Creates a new chat group.
     * 
     * @param contractNumber  The contract number associated with the user creating
     *                        the group.
     * @param createChatGroup The request containing details of the new chat group.
     * @return A ChatGroupResponse object representing the newly created chat group.
     */
    public ChatGroupResponse createGroup(String contractNumber, CreateChatGroup createChatGroup);

    /**
     * Adds a message to a specific chat group.
     * 
     * @param contractNumber The contract number associated with the user adding the
     *                       message.
     * @param messageRequest The request containing details of the message to be
     *                       added.
     * @param groupId        The unique identifier of the chat group to which the
     *                       message is added.
     * @return A ChatGroupResponse object representing the updated chat group.
     */
    public ChatGroupResponse addMessage(String contractNumber, MessageRequest messageRequest, UUID groupId);
}
