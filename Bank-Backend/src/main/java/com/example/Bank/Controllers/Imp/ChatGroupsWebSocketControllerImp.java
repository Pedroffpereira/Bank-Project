package com.example.Bank.Controllers.Imp;

import java.util.List;
import java.util.UUID;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Bank.Request.CreateChatGroup;
import com.example.Bank.Request.GetAllChatGroupsRequest;
import com.example.Bank.Request.MessageRequest;
import com.example.Bank.Response.ChatGroupResponse;
import com.example.Bank.Services.ChatGroupService;
import com.example.Bank.Services.Imp.ChatGroupServiceImp;

import jakarta.validation.Valid;

/**
 * WebSocket controller for handling chat group operations.
 */
@Controller
public class ChatGroupsWebSocketControllerImp {
    /**
     * The ChatGroupService for handling chat group operations.
     * 
     * @author Pedro Pereira
     */
    final ChatGroupService chatGroupService;

    /**
     * Constructs a new instance of ChatGroupsWebSocketControllerImp.
     * 
     * @param chatGroupService The ChatGroupService implementation.
     */
    public ChatGroupsWebSocketControllerImp(ChatGroupServiceImp chatGroupService) {
        this.chatGroupService = chatGroupService;
    }

    /**
     * Retrieves all chat groups.
     * 
     * @param getAllChatGroupsRequest The request payload.
     * @return The list of chat groups.
     */
    @MessageMapping("/getallchatgroups")
    @SendTo("/topic/getall")
    public List<ChatGroupResponse> getChatGroup(@Payload GetAllChatGroupsRequest getAllChatGroupsRequest) {

        return chatGroupService.getChatGroup(getAllChatGroupsRequest.getJwt().getSubject(),
                getAllChatGroupsRequest.getPage(), getAllChatGroupsRequest.getSize());
    }

    /**
     * Retrieves a specific chat group.
     * 
     * @param jwt The JWT token of the authenticated user.
     * @param id  The ID of the chat group to retrieve.
     * @return The chat group response.
     */
    @MessageMapping("/getchatgroup")
    @SendTo("/topic/get")
    public ChatGroupResponse getChatGroup(@AuthenticationPrincipal Jwt jwt, @Payload UUID id) {

        return chatGroupService.showGroup(jwt.getSubject(), id);
    }

    /**
     * Creates a new chat group.
     * 
     * @param jwt             The JWT token of the authenticated user.
     * @param createChatGroup The request payload for creating a chat group.
     * @return The created chat group response.
     */
    @MessageMapping("/createchatgroup")
    @SendTo("/topic/post")
    public ChatGroupResponse createChatGroup(@AuthenticationPrincipal Jwt jwt,
            @Payload @Valid CreateChatGroup createChatGroup) {

        return this.chatGroupService.createGroup(jwt.getSubject(), createChatGroup);
    }

    /**
     * Adds a message to a chat group.
     * 
     * @param jwt            The JWT token of the authenticated user.
     * @param messageRequest The request payload for adding a message.
     * @param id             The ID of the chat group.
     * @return The updated chat group response.
     */
    @MessageMapping("/addMessage")
    @SendTo("/topic/post/message")
    public ChatGroupResponse AddMessageToChatGroup(@AuthenticationPrincipal Jwt jwt,
            @Payload @Valid MessageRequest messageRequest, @PathVariable UUID id) {

        return this.chatGroupService.addMessage(jwt.getSubject(), messageRequest, id);
    }
}
