package com.example.Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of the Spring Boot application for the bank system.
 * This class contains the main method to start the application.
 * 
 * <p>
 * This class is annotated with {@code @SpringBootApplication}, indicating
 * that it's the entry point of the Spring Boot application and enabling
 * auto-configuration and component scanning.
 * </p>
 * 
 * <p>
 * Usage: Run this class to start the Spring Boot application for the bank
 * system.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
@SpringBootApplication
public class BankApplication {
	/**
	 * The main method to start the Spring Boot application.
	 * 
	 * <p>
	 * This method initializes and runs the Spring Boot application.
	 * </p>
	 * 
	 * @param args The command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
