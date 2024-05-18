package com.example.Bank.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing user-related operations and authentication.
 * Extends {@code UserDetailsService} to provide user authentication
 * functionality.
 * 
 * <p>
 * This interface declares a method to load user details by username.
 * </p>
 * 
 * <p>
 * Usage: Implement this interface to define user management and authentication
 * operations.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@Service
public interface UserService extends UserDetailsService {
    /**
     * Loads user details by username.
     * 
     * <p>
     * This method is responsible for loading user details based on the provided
     * username.
     * </p>
     * 
     * @param username The username of the user whose details are to be loaded.
     * @return The UserDetails object containing the details of the user.
     */
    public UserDetails loadUserByUsername(String username);
}
