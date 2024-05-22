package com.example.Bank.Configurations;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Configuration settings for bank details.
 * <p>
 * This class is annotated with {@code @Component} to indicate that it is a
 * Spring bean.
 * The {@code @Data} annotation from Lombok automatically generates getters,
 * setters,
 * toString, equals, and hashCode methods.
 * </p>
 */
@Component
@Data
public class BankSettings {
    /**
     * The country code of the bank. Default is "PT".
     */
    String countryCode = "PT";
    /**
     * The check digit used in bank operations. Default is "50".
     */
    String checkDigit = "50";
    /**
     * The code of the bank. Default is "0001".
     */
    String bankCode = "0001";
    /**
     * The branch code of the bank. Default is "0123".
     */
    String brenchCode = "0123";
    /**
     * The national digit used in bank operations. Default is "54".
     */
    String nationalDigit = "54";
}
