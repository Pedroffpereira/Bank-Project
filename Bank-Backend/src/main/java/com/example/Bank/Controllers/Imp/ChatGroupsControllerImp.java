package com.example.Bank.Controllers.Imp;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Controllers.ChatGroupsController;
import com.example.Bank.Request.CreateChatGroup;
import com.example.Bank.Request.MessageRequest;
import com.example.Bank.Response.ChatGroupResponse;
import com.example.Bank.Services.ChatGroupService;
import com.example.Bank.Services.Imp.ChatGroupServiceImp;

import jakarta.validation.Valid;

/**
 * Implementation of the ChatGroupsController interface for handling chat
 * group-related endpoints.
 * 
 * This class implements the ChatGroupsController interface and provides
 * implementations for handling chat group-related endpoints such as retrieving,
 * creating, and adding messages to chat groups.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/chatgroups")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ChatGroupsControllerImp implements ChatGroupsController {
    /**
     * The ChatGroupService for handling chat group operations.
     */
    ChatGroupService chatGroupService;

    /**
     * Constructor for the ChatGroupsControllerImp class.
     * 
     * Initializes the ChatGroupsControllerImp with the provided ChatGroupService.
     * 
     * @param chatGroupServiceImp The ChatGroupService to use for chat group
     *                         operations.
     */
    public ChatGroupsControllerImp(ChatGroupServiceImp chatGroupServiceImp) {
        this.chatGroupService = chatGroupServiceImp;
    }

    /**
     * Get a list of chat groups.
     * 
     * This method handles the endpoint to retrieve a list of chat groups.
     * It takes authentication details, pagination parameters, and returns
     * a list of ChatGroupResponse objects representing the chat groups.
     * 
     * @param jwt  The JWT token containing authentication information.
     * @param page The page number for pagination (default: 0).
     * @param size The page size for pagination (default: 10).
     * @return A list of ChatGroupResponse objects representing the chat groups.
     */
    @Override
    @GetMapping()
    public List<ChatGroupResponse> getChatGroup(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        return chatGroupService.getChatGroup(jwt.getSubject(), page, size);
    }

    /**
     * Get a chat group by ID.
     * 
     * This method handles the endpoint to retrieve a chat group by its ID.
     * It takes authentication details and the ID of the chat group to retrieve,
     * and returns a ChatGroupResponse object representing the chat group.
     * 
     * @param jwt The JWT token containing authentication information.
     * @param id  The ID of the chat group to retrieve.
     * @return A ChatGroupResponse object representing the retrieved chat group.
     */
    @Override
    @GetMapping("{id}")
    public ChatGroupResponse getChatGroup(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id) {

        return chatGroupService.showGroup(jwt.getSubject(), id);
    }

    @Override
    @PostMapping
    public ChatGroupResponse createChatGroup(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid CreateChatGroup createChatGroup) {

        return this.chatGroupService.createGroup(jwt.getSubject(), createChatGroup);
    }

    /**
     * Add a message to a chat group.
     * 
     * This method handles the endpoint to add a message to a chat group.
     * It takes authentication details, a request object containing the message
     * to add, and the ID of the chat group, and returns a ChatGroupResponse
     * object representing the updated chat group.
     * 
     * @param jwt            The JWT token containing authentication information.
     * @param messageRequest The request object containing the message to add.
     * @param id             The ID of the chat group to which the message will be
     *                       added.
     * @return A ChatGroupResponse object representing the updated chat group.
     */
    @Override
    @PostMapping("{id}/message")
    public ChatGroupResponse AddMessageToChatGroup(@AuthenticationPrincipal Jwt jwt,
            @RequestBody @Valid MessageRequest messageRequest, @PathVariable UUID id) {

        return this.chatGroupService.addMessage(jwt.getSubject(), messageRequest, id);
    }
}
