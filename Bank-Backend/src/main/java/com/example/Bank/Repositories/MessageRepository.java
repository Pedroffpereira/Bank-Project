package com.example.Bank.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entaties.Message;

/**
 * Repository interface for accessing message data.
 * 
 * This interface provides methods to interact with the message data
 * stored in the database, such as finding messages by the associated account
 * number.
 * 
 * @author Pedro Pereira
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    /**
     * Find messages by the account number of the associated account.
     * 
     * This method searches for messages in the database based on the account number
     * of the associated account, with pagination support.
     * 
     * @param accountNumber The account number associated with the messages.
     * @param pageable      Pagination information.
     * @return A list of messages associated with the specified account number.
     */
    public List<Message> findByAccount_AccountNumber(String accountNumber, Pageable pageable);
}