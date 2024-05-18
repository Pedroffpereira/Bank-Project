package com.example.Bank.Services.Imp;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Entaties.User;
import com.example.Bank.Mappers.DTOMapper;
import com.example.Bank.Mappers.Imp.AccountMapper;
import com.example.Bank.Repositories.AccountRepository;
import com.example.Bank.Repositories.UserRepository;
import com.example.Bank.Request.CreateAccount;
import com.example.Bank.Response.AccoutResponse;
import com.example.Bank.Response.CreateAccountResponse;
import com.example.Bank.Response.JWTResponse;
import com.example.Bank.Services.AuthenticationService;
import com.example.Bank.Services.TokenService;
import com.example.Bank.Utilaties.IbanGeneratorUtility;

/**
 * Service implementation for user authentication and account management.
 * 
 * <p>
 * This class implements the AuthenticationService interface and provides
 * methods for user authentication,
 * account registration, and account authentication.
 * </p>
 * 
 * <p>
 * Usage: Implement this class to handle user authentication and account-related
 * operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public class AuthenticationServiceImp implements AuthenticationService {
        /**
         * The AuthenticationManager used for user authentication.
         */
        private final AuthenticationManager authenticationManager;

        /**
         * The TokenService used for generating JWT tokens.
         */
        private final TokenService tokenService;

        /**
         * The AccountRepository used for accessing account data.
         */
        private final AccountRepository accountRepository;

        /**
         * The UserRepository used for accessing user data.
         */
        private final UserRepository userRepository;

        /**
         * The PasswordEncoder used for encoding passwords.
         */
        private final PasswordEncoder passwordEncoder;

        /**
         * The IbanGeneratorUtility used for generating IBANs.
         */
        private final IbanGeneratorUtility ibanGeneratorUtility;

        /**
         * Constructs a new AuthenticationServiceImp instance with the specified
         * dependencies.
         * 
         * @param authenticationManager The authentication manager for authenticating
         *                              users.
         * @param tokenService          The service for generating JWT tokens.
         * @param accountRepository     The repository for accessing account data.
         * @param userRepository        The repository for accessing user data.
         * @param passwordEncoder       The password encoder for encoding passwords.
         * @param ibanGeneratorUtility  The utility for generating IBANs.
         */
        AuthenticationServiceImp(AuthenticationManager authenticationManager, TokenService tokenService,
                        AccountRepository accountRepository, UserRepository userRepository,
                        PasswordEncoder passwordEncoder, IbanGeneratorUtility ibanGeneratorUtility) {

                this.authenticationManager = authenticationManager;
                this.tokenService = tokenService;
                this.accountRepository = accountRepository;
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
                this.ibanGeneratorUtility = ibanGeneratorUtility;
        }

        /**
         * Authenticates a user with the specified username and password.
         * 
         * @param username The username of the user to authenticate.
         * @param password The password of the user.
         * @return The JWT response containing the authentication token.
         */
        @Override
        public JWTResponse loginUser(String username, String password) {
                Authentication auth = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(username, password));

                return JWTResponse.builder().token(tokenService.generateJwt(auth)).build();
        }

        /**
         * Registers a new account with the specified details.
         * 
         * @param createAccount The details of the account to be registered.
         * @return The response containing the IBAN and contract number of the created
         *         account.
         */
        @Override
        public CreateAccountResponse RegistrationAccount(CreateAccount createAccount) {
                User user = User.builder()
                                .name(createAccount.getName())
                                .email(createAccount.getEmail())
                                .build();
                String accout = ibanGeneratorUtility.acccontNumber();
                String iban = ibanGeneratorUtility.generate(accout);
                user = userRepository.save(user);
                Account account = Account.builder()
                                .accountNumber(accout)
                                .iban(iban)
                                .password(passwordEncoder.encode(createAccount.getPassword()))
                                .balance(1000D)
                                .state('A')
                                .user(user)
                                .build();

                accountRepository.save(account);
                return CreateAccountResponse.builder()
                                .iban(iban)
                                .contractNumber(accout)
                                .build();
        }

        /**
         * Retrieves the details of the account associated with the specified contract
         * number.
         * 
         * @param contractNumber The contract number associated with the account.
         * @return The account response containing account details.
         * @throws IllegalAccessError if the account does not exist.
         */
        @Override
        public AccoutResponse auth(String contractNumber) {
                DTOMapper<Account, AccoutResponse> accountMapper = new AccountMapper();
                Optional<Account> accountOptional = accountRepository.findByAccountNumber(contractNumber);
                if (accountOptional.isEmpty()) {
                        throw new IllegalAccessError("error");
                }
                Account account = accountOptional.get();

                return accountMapper.toDTO(account);
        }
}
