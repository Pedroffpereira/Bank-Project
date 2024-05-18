package com.example.Bank.Entaties;

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
 * Table class Transactions
 * 
 * 
 * 
 * @author Pedro Pereira
 * 
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Transactions")
public class Transaction extends AbstractEntaties {

    /**
     * Description of the transaction.
     * 
     * This field provides a brief description of the transaction.
     */
    String description;
    /**
     * Amount of the transaction.
     * 
     * This field represents the amount of money involved in the transaction.
     */
    Double amount;
    /**
     * Balance after the transaction.
     * 
     * This field represents the account balance after the transaction is completed.
     */
    Double balance_after_transaction;
    /**
     * Type of the transaction.
     * 
     * This field indicates the type of transaction, such as debit or credit.
     */
    Type type;
    /**
     * IBAN associated with the transaction.
     * 
     * This field represents the International Bank Account Number (IBAN) related to
     * the transaction.
     */
    String iban;
    /**
     * Account associated with the transaction.
     * 
     * This field represents a many-to-one relationship with the Account entity.
     * The "account_id" column in the "Transactions" table is a foreign key
     * referencing
     * the "Accounts" table.
     */
    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
}
