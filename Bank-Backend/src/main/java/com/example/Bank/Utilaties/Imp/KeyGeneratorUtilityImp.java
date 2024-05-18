package com.example.Bank.Utilaties.Imp;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import com.example.Bank.Utilaties.KeyGeneratorUtility;

/**
 * Implementation of the key generator utility.
 * 
 * @author Pedro Pereira
 */
public class KeyGeneratorUtilityImp implements KeyGeneratorUtility {
    /**
     * Generates an RSA key pair.
     * 
     * @return The generated RSA key pair.
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
