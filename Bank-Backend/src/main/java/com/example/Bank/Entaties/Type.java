package com.example.Bank.Entaties;

import jakarta.persistence.Entity;

/**
 * Enum representing the type of a transaction.
 * 
 * This enum defines the different types of transactions that can occur,
 * such as withdrawals, deposits, debit transfers, and credit transfers.
 * 
 * @author Pedro Pereira
 */
public enum Type {
    /**
     * Represents a withdrawal transaction.
     * 
     * This type indicates that money is being taken out of the account.
     */
    Withdraw,
    /**
     * Represents a deposit transaction.
     * 
     * This type indicates that money is being added to the account.
     */
    Deposit,
    /**
     * Represents a debit transfer transaction.
     * 
     * This type indicates a transfer of money out of the account to another
     * account.
     */
    DebitTransfers,
    /**
     * Represents a credit transfer transaction.
     * 
     * This type indicates a transfer of money into the account from another
     * account.
     */
    CreditTransfer
}
