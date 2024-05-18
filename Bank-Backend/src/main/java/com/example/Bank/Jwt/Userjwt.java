package com.example.Bank.Jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a user's authentication details implementing UserDetails
 * interface.
 * 
 * This class provides authentication details for a user, implementing the
 * UserDetails interface
 * required by Spring Security for user authentication and authorization.
 * 
 * @author Pedro Pereira
 */
@AllArgsConstructor
@Data
public class Userjwt implements UserDetails {
	/**
	 * The username field represents the contract number for authentication.
	 */
	String username;
	/**
	 * The password field represents the password for authentication.
	 */
	String password;

	/**
	 * Returns the authorities granted to the user.
	 *
	 * @return A collection of authorities granted to the user.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Implement logic to retrieve authorities, or return null if not applicable
		return null;
	}

	/**
	 * Returns the password used to authenticate the user.
	 *
	 * @return The user's password.
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the username used to authenticate the user.
	 *
	 * @return The user's username.
	 */
	@Override
	public String getUsername() {
		return this.username;
	}

	// Methods related to account status (e.g., locking) can be added if needed

	/**
	 * Indicates whether the user's account has expired.
	 *
	 * @return true if the user's account is valid (i.e., not expired), false
	 *         otherwise.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true; // Implement logic for account expiration, if needed
	}

	/**
	 * Indicates whether the user is locked or unlocked.
	 *
	 * @return true if the user is not locked, false otherwise.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true; // Implement logic for account locking, if needed
	}

	/**
	 * Indicates whether the user's credentials (password) has expired.
	 *
	 * @return true if the user's credentials are valid (i.e., not expired), false
	 *         otherwise.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Implement logic for credentials expiration, if needed
	}

	/**
	 * Indicates whether the user is enabled or disabled.
	 *
	 * @return true if the user is enabled, false otherwise.
	 */
	@Override
	public boolean isEnabled() {
		return true; // Implement logic for user enabling/disabling, if needed
	}
}
