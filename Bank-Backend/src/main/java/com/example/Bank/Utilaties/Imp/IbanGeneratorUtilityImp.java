package com.example.Bank.Utilaties.Imp;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.Bank.Utilaties.IbanGeneratorUtility;

import lombok.Data;
/**
 * Implementation of the IBAN generator utility.
 * 
 * @author Pedro Pereira
 */
@Component
@Data
public class IbanGeneratorUtilityImp implements IbanGeneratorUtility {
    /**
     * Generates a random account number.
     * 
     * @return The randomly generated account number.
     */
    @Override
    public String acccontNumber() {

        Random rnd = new Random();
        Long number = rnd.nextLong(99999999999L);

        return String.format("%11d", number);
    }

    /**
     * Generates an IBAN using the given account number.
     * 
     * @param accountNumber The account number to generate the IBAN from.
     * @return The generated IBAN.
     */
    @Override
    public String generate(String accountNumber) {
        String countryCode = "PT";
        String checkDigit = "50";
        String bankCode = "0001";
        String brenchCode = "0123";
        String nationalDigit = "54";
        // this will convert any number sequence into 6 character.
        return countryCode + checkDigit + bankCode + brenchCode + accountNumber + nationalDigit;
    }
}
