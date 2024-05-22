package com.example.Bank.Services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Bank.Entaties.Account;
import com.example.Bank.Jwt.Userjwt;
import com.example.Bank.Repositories.AccountRepository;

@SpringBootTest
public class UserServiceImpTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    UserService userService;

    @Test
    void loadUserByUsernameTest() {

        Account account = Account.builder().accountNumber("890890870")
                .password("$2a$10$PDRzbZjBzCuEzsK33FmEmOBg3AYu1VIcEA8sHTl1a0zMltuKeCOj2")
                .build();

        when(accountRepository.findByAccountNumber("890890870")).thenReturn(Optional.of(account));
        UserDetails teste = new Userjwt(account.getAccountNumber(), account.getPassword());
        assertThat(userService.loadUserByUsername((ArgumentMatchers.any()))).isEqualTo(null);
        assertThat(userService.loadUserByUsername((ArgumentMatchers.any()))).isEqualTo(null);
    }
}
