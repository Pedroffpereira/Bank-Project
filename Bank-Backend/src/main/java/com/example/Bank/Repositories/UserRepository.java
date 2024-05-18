package com.example.Bank.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Entaties.User;

/**
 * Repository interface for accessing user data.
 * 
 * This interface provides methods to interact with the user data
 * stored in the database.
 * 
 * @author Pedro Pereira
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}