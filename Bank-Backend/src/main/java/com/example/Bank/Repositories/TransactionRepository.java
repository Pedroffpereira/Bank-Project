package com.example.Bank.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entaties.Transaction;

/**
 * Repository interface for accessing transaction data.
 * 
 * This interface provides methods to interact with the transaction data
 * stored in the database, such as finding transactions by the associated
 * account number.
 * 
 * @author Pedro Pereira
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    /**
     * Find transactions by the account number of the associated account.
     * 
     * This method searches for transactions in the database based on the account
     * number
     * of the associated account, with pagination support.
     * 
     * @param accountNumber The account number associated with the transactions.
     * @param pageable      Pagination information.
     * @return A list of transactions associated with the specified account number.
     */
    public List<Transaction> findByAccount_AccountNumber(String accountNumber, Pageable pageable);

}