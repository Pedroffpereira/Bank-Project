package com.example.Bank.Entaties;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a message in a chat group.
 * 
 * This class maps to the "Messages" table in the database and contains
 * details about a message such as the text content, the associated chat group,
 * and the account that sent the message.
 * 
 * @author Pedro Pereira
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Messages")
public class Message extends AbstractEntaties {
    /**
     * Text content of the message.
     */
    String text;
    /**
     * The chat group to which the message belongs.
     * 
     * This field represents a many-to-one relationship with the ChatGroup entity.
     * The "chatgroup_id" column in the "Messages" table is a foreign key
     * referencing
     * the "ChatGroups" table.
     */
    @ManyToOne
    @JoinColumn(name = "chatgroup_id")
    ChatGroup chatGroup;
    /**
     * The account that sent the message.
     * 
     * This field represents a many-to-one relationship with the Account entity.
     * The "account_id" column in the "Messages" table is a foreign key referencing
     * the "Accounts" table.
     */
    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
}
