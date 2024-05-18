package com.example.Bank.Entaties;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a chat group.
 * 
 * This class maps to the "ChatGroups" table in the database and contains
 * details about a chat group such as the description, list of messages,
 * and list of accounts participating in the chat group.
 * 
 * @author Pedro Pereira
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ChatGroups")
public class ChatGroup extends AbstractEntaties {
    /**
     * Description or name of the chat group.
     * 
     * This field holds the description or name of the chat group.
     */
    String description;
    /**
     * List of messages in the chat group.
     * 
     * This field represents a one-to-many relationship with the Message entity.
     * It is mapped by the "chatGroup" property in the Message class.
     */
    @OneToMany(mappedBy = "chatGroup")
    List<Message> messages;
    /**
     * List of accounts participating in the chat group.
     * 
     * This field represents a many-to-many relationship with the Account entity.
     * It indicates the accounts that are members of the chat group.
     */
    @ManyToMany
    List<Account> accounts;
}
