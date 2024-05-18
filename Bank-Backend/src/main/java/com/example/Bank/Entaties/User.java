package com.example.Bank.Entaties;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a user.
 * 
 * This class maps to the "Users" table in the database and contains
 * details about a user such as the name, email, and associated accounts.
 * 
 * @author Pedro Pereira
 */
@Entity
@Table(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends AbstractEntaties {
    /**
     * Name of the user.
     * 
     * This field stores the name of the user.
     */
    @NotNull
    String name;
    /**
     * Email address of the user.
     * 
     * This field stores the email address of the user.
     */
    @NotNull
    @Email
    @Column(unique = true)
    String email;
    /**
     * List of accounts associated with the user.
     * 
     * This field represents a one-to-many relationship with the Account entity.
     * It indicates the accounts that are owned by the user.
     */
    @OneToMany(mappedBy = "user")
    List<Account> accounts;
}
