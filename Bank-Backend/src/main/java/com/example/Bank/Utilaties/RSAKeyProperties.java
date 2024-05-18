package com.example.Bank.Utilaties;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Component class for managing RSA key properties.
 * 
 * This class provides properties for managing RSA public and private keys.
 * It generates a new key pair upon instantiation.
 * 
 * @author Pedro Pereira
 */

@Component
@Data
public class RSAKeyProperties {
    /**
     * The RSA public key.
     * 
     * This field stores the RSA public key.
     */
    private RSAPublicKey publicKey;
    /**
     * The RSA private key.
     * 
     * This field stores the RSA private key.
     */
    private RSAPrivateKey privateKey;

    /**
     * Default constructor.
     * 
     * This constructor initializes the RSA key pair by generating
     * a new key pair using a utility method.
     */
    public RSAKeyProperties() {
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }
}
