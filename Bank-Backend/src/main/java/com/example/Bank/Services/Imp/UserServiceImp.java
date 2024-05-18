package com.example.Bank.Services.Imp;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Jwt.Userjwt;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Services.UserService;

/**
 * Service implementation for managing user details.
 * 
 * <p>
 * This class implements the UserDetailsService interface and provides methods
 * to load user details
 * based on the username.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to define user details management operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class UserServiceImp implements UserService {
    /**
     * The repository for accessing account data.
     */
    private final AccountRepository accountRepository;

    /**
     * Constructs a new UserServiceImp instance with the specified
     * AccountRepository.
     * 
     * @param accountRepository The repository for accessing account data.
     */
    public UserServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Loads user details based on the provided username.
     * 
     * @param username The username of the user to load.
     * @return A UserDetails object representing the loaded user details.
     * @throws UsernameNotFoundException If the specified username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Account> optionalAccount = this.accountRepository.findByAccountNumber(username);
        if (optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException("Utilizador não existe");
        }
        Account account = optionalAccount.get();

        return new Userjwt(account.getAccountNumber(), account.getPassword());
    }

}