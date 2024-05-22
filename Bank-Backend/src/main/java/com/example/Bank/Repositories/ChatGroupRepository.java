package com.example.Bank.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entaties.ChatGroup;

/**
 * Repository interface for accessing chat group data.
 * 
 * This interface provides methods to interact with the chat group data
 * stored in the database, such as finding chat groups by ID, account number,
 * or account ID.
 * 
 * @author Pedro Pereira
 */
@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, UUID> {
    /**
     * /**
     * Find a chat group by its ID and associated account number.
     * 
     * This method searches for a chat group in the database based on its unique ID
     * and the associated account number.
     * 
     * @param id            The unique identifier of the chat group.
     * @param accountNumber The account number associated with the chat group.
     * @return An Optional containing the found chat group, or empty if not found.
     */
    public Optional<ChatGroup> findByIdAndAccounts_AccountNumber(UUID id, String accountNumber);

    /**
     * Find chat groups by the ID of an associated account.
     * 
     * This method searches for chat groups in the database based on the unique ID
     * of an associated account.
     * 
     * @param accountId The unique identifier of the associated account.
     * @return A list of chat groups associated with the specified account ID.
     */
    public List<ChatGroup> findByAccounts_id(UUID accountId);

    /**
     * Find chat groups by the account number of associated accounts.
     * 
     * This method searches for chat groups in the database based on the account
     * number
     * of associated accounts, with pagination support.
     * 
     * @param accountNumber The account number associated with the chat groups.
     * @param pageable      Pagination information.
     * @return An Optional containing a list of chat groups associated with the
     *         specified account number, or empty if not found.
     */
    public List<ChatGroup> findByAccounts_AccountNumber(String accountNumber, Pageable pageable);
}