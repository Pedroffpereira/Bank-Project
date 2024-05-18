package com.example.Bank.Entaties;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table class Accounts
 * 
 * Represents a bank account with details such as account number, balance,
 * password, IBAN, associated user, transactions, and state.
 * 
 * 
 * 
 * @author Pedro Pereira
 * 
 */
@Entity
@Table(name = "Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends AbstractEntaties {
    /**
     * Unique number of the account used for login.
     */
    @Column(unique = true, nullable = false)
    String accountNumber;

    /**
     * Balance of the account.
     * 
     * This field represents the current balance of the account.
     */
    @Column(nullable = false)
    @NotNull
    Double balance;
    /**
     * Encrypted password of the account.
     * 
     * This field stores the encrypted password for the account.
     */
    @Column(nullable = false)
    @NotNull
    String password;
    /**
     * IBAN of the account.
     * 
     * This field represents the International Bank Account Number (IBAN) of the
     * account.
     */
    @Column(unique = true, nullable = false)
    @NotNull
    String iban;

    /**
     * User associated with the account.
     * 
     * This field represents a many-to-one relationship with the User entity.
     * The "user_id" column in the "Accounts" table is a foreign key referencing
     * the "Users" table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    /**
     * List of transactions associated with the account.
     * 
     * This field represents a one-to-many relationship with the Transaction entity.
     * It is mapped by the "account" property in the Transaction class.
     */
    @OneToMany(mappedBy = "account")
    List<Transaction> transactions;

    /**
     * State of the account indicating if it is active or not.
     * 
     * This field represents the current state of the account, where 'A' could stand
     * for active and 'I' for inactive, for example.
     */
    @Column(nullable = false)
    @NotNull
    char state;
}