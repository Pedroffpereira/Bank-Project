package com.example.Bank.Utilaties.Imp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Bank.Utilaties.IbanGeneratorUtility;
import com.example.Bank.Configurations.BankSettings;
import lombok.Data;

/**
 * Implementation of the IBAN generator utility.
 * 
 * @author Pedro Pereira
 */
@Component
@Data
public class IbanGeneratorUtilityImp implements IbanGeneratorUtility {
    @Autowired
    BankSettings BankSettings;

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
        String countryCode = BankSettings.getCountryCode();
        String checkDigit = BankSettings.getCheckDigit();
        String bankCode = BankSettings.getBankCode();
        String brenchCode = BankSettings.getBrenchCode();
        String nationalDigit = BankSettings.getNationalDigit();
        return countryCode + checkDigit + bankCode + brenchCode + accountNumber + nationalDigit;
    }
}
