package com.example.Bank.Utilaties;

/**
 * Utility interface for generating IBANs.
 * 
 * This interface provides methods for generating IBANs based on account
 * numbers.
 * 
 * @author Pedro Pereira
 */
public interface IbanGeneratorUtility {
    /**
     * Generate an account number.
     * 
     * This method generates a random account number.
     * 
     * @return The generated account number.
     */
    public String acccontNumber();

    /**
     * Generate an IBAN from an account number.
     * 
     * This method generates an International Bank Account Number (IBAN)
     * based on the provided account number.
     * 
     * @param accountNumber The account number for which to generate the IBAN.
     * @return The generated IBAN.
     */
    public String generate(String accountNumber);
}
