package com.example.Bank.Utilaties;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Utility interface for generating RSA keys.
 * 
 * This interface provides a static method to generate RSA key pairs.
 * 
 * @author Pedro Pereira
 */
public interface KeyGeneratorUtility {
    /**
     * Generate an RSA key pair.
     * 
     * This method generates an RSA key pair with a key size of 2048 bits.
     * 
     * @return The generated RSA key pair.
     * @throws IllegalStateException if an error occurs during key generation.
     */
    public static KeyPair generateRsaKey() {
        KeyPair keyPair;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException();
        }

        return keyPair;
    }

}