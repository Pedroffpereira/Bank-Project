package com.example.Bank.Services.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.Transaction;
import com.example.Bank.Entaties.Type;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Mappers.Imp.TransactionsMapper;
import com.example.Bank.Repositories.TransactionRepository;
import com.example.Bank.Response.TransactionResponse;
import com.example.Bank.Services.TrasactionService;

/**
 * Service implementation for managing transactions.
 * 
 * <p>
 * This class implements the TrasactionService interface and provides methods to
 * retrieve transactions,
 * insert transfers, withdrawals, and deposits.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to define transaction management operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class TrasactionServiceImp implements TrasactionService {
    /**
     * The repository for accessing transaction data.
     */
    final TransactionRepository transactionRepository;
    /**
     * The Mapper for convert entatie to DTO.
     */
    final DTOMapper<Transaction, TransactionResponse> transactionsMapper;

    /**
     * Constructs a new TrasactionServiceImp instance with the specified
     * TransactionRepository.
     * 
     * @param transactionRepository The repository for accessing transaction data.
     * @param transactionsMapper    The Mapper for convert entatie to DTO.
     */
    TrasactionServiceImp(TransactionRepository transactionRepository, TransactionsMapper transactionsMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionsMapper = transactionsMapper;
    }

    /**
     * Retrieves a list of transactions associated with the specified account
     * number.
     * 
     * @param accountNumber The account number associated with the transactions to
     *                      retrieve.
     * @param page          The page number of the result set to retrieve.
     * @param size          The size of each page in the result set.
     * @return A list of TransactionResponse objects representing the retrieved
     *         transactions.
     */
    @Override
    public List<TransactionResponse> getTransaction(String accountNumber, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Transaction> transactions = transactionRepository.findByAccount_AccountNumber(accountNumber, pageable);
        List<TransactionResponse> transactionResponseList = new ArrayList<TransactionResponse>();
        for (Transaction transaction : transactions) {
            transactionResponseList.add(transactionsMapper.toDTO(transaction));
        }
        return transactionResponseList;
    }

    /**
     * Inserts a transfer transaction between two accounts.
     * 
     * @param account         The account initiating the transfer.
     * @param amount          The amount to transfer.
     * @param transferAccount The account to receive the transfer.
     * @return A TransactionResponse object representing the inserted transfer
     *         transaction.
     */
    @Override
    public TransactionResponse insertTransfer(Account account, Double amount, Account transferAccount) {

        Transaction transactionCurrentAccount = Transaction.builder()
                .account(account)
                .amount(amount)
                .description("Transferencia de " + amount.toString() + "€ para a conta do" + transferAccount.getIban())
                .balance_after_transaction(account.getBalance())
                .type(Type.CreditTransfer)
                .build();

        if (transferAccount != null) {
            Transaction transactionTransferAccount = Transaction.builder()
                    .account(transferAccount)
                    .amount(amount)
                    .description("Transferencia de " + amount.toString() + "€ da conta " + account.getIban())
                    .balance_after_transaction(transferAccount.getBalance())
                    .type(Type.DebitTransfers).build();

            this.transactionRepository.save(transactionTransferAccount);
        }
        return transactionsMapper.toDTO(this.transactionRepository.save(transactionCurrentAccount));

    }

    @Override
    public TransactionResponse insertOutTransfer(Account account, Double amount) {

        Transaction transactionCurrentAccount = Transaction.builder()
                .account(account)
                .amount(amount)
                .description("Transferencia de " + amount.toString() + "€ para conta externa")
                .balance_after_transaction(account.getBalance())
                .type(Type.CreditTransfer)
                .build();

        return transactionsMapper.toDTO(this.transactionRepository.save(transactionCurrentAccount));

    }

    /**
     * Inserts a withdrawal transaction for the specified account.
     * 
     * @param account The account from which the withdrawal is made.
     * @param amount  The amount to withdraw.
     * @return A TransactionResponse object representing the inserted withdrawal
     *         transaction.
     */
    @Override
    public TransactionResponse InsertWithdrawal(Account account, Double amount) {

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .description("Levantamento de " + amount.toString() + "€")
                .balance_after_transaction(account.getBalance())
                .type(Type.Withdraw)
                .build();

        return transactionsMapper.toDTO(this.transactionRepository.save(transaction));
    }

    /**
     * Inserts a deposit transaction for the specified account.
     * 
     * @param account The account to which the deposit is made.
     * @param amount  The amount to deposit.
     * @return A TransactionResponse object representing the inserted deposit
     *         transaction.
     */
    @Override
    public TransactionResponse InsertDeposit(Account account, Double amount) {
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .description("Deposito de " + amount.toString() + "€")
                .balance_after_transaction(account.getBalance())
                .type(Type.Deposit)
                .build();

        return transactionsMapper.toDTO(this.transactionRepository.save(transaction));
    }
}
