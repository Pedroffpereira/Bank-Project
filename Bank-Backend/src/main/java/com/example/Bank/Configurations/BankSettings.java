package com.example.Bank.Configurations;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BankSettings {
    String countryCode = "PT";
    String checkDigit = "50";
    String bankCode = "0001";
    String brenchCode = "0123";
    String nationalDigit = "54";
}
