package com.example.Bank.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entaties.Account;

/**
 * Repository interface for accessing account data.
 * 
 * This interface provides methods to interact with the account data
 * stored in the database, such as finding accounts by account number
 * or IBAN.
 * 
 * @author Pedro Pereira
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    /**
     * Find an account by its account number.
     * 
     * This method searches for an account in the database based on its unique
     * account number.
     * 
     * @param accountNumber The account number to search for.
     * @return An Optional containing the found account, or empty if not found.
     */
    public Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Find an account by its IBAN (International Bank Account Number).
     * 
     * This method searches for an account in the database based on its unique
     * IBAN.
     * 
     * @param iban The IBAN to search for.
     * @return An Optional containing the found account, or empty if not found.
     */
    public Optional<Account> findByIban(String iban);
}