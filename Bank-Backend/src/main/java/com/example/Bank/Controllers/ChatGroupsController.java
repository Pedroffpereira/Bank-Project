package com.example.Bank.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Request.CreateChatGroup;
import com.example.Bank.Request.MessageRequest;
import com.example.Bank.Response.ChatGroupResponse;

import jakarta.validation.Valid;

/**
 * Interface for handling chat group-related endpoints.
 * 
 * This interface defines methods for handling endpoints related to chat groups
 * in the application.
 * 
 * @author Pedro Pereira
 */
@RequestMapping("api/v1/chatgroups")
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public interface ChatGroupsController {
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
        public List<ChatGroupResponse> getChatGroup(@AuthenticationPrincipal Jwt jwt,
                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size);

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

        public ChatGroupResponse getChatGroup(@AuthenticationPrincipal Jwt jwt, @PathVariable UUID id);

        /**
         * Create a new chat group.
         * 
         * This method handles the endpoint to create a new chat group.
         * It takes authentication details and a request object containing
         * information about the new chat group to create, and returns
         * a ChatGroupResponse object representing the created chat group.
         * 
         * @param jwt             The JWT token containing authentication information.
         * @param createChatGroup The request object containing information about the
         *                        new chat group.
         * @return A ChatGroupResponse object representing the created chat group.
         */

        public ChatGroupResponse createChatGroup(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody @Valid CreateChatGroup createChatGroup);

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
        public ChatGroupResponse AddMessageToChatGroup(@AuthenticationPrincipal Jwt jwt,
                        @RequestBody @Valid MessageRequest messageRequest, @PathVariable UUID id);
}
