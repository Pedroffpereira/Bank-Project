package com.example.Bank;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet initializer class for the bank system.
 * Extends {@code SpringBootServletInitializer} to configure the application
 * when running as a WAR file.
 * 
 * <p>
 * This class overrides the {@code configure} method to specify the main
 * application class.
 * </p>
 * 
 * <p>
 * Usage: This class is automatically used by Spring Boot to configure the
 * application when running as a WAR file.
 * </p>
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {
	/**
	 * Configures the Spring application builder with the main application class.
	 * 
	 * <p>
	 * This method is overridden to specify the main application class when running
	 * as a WAR file.
	 * </p>
	 * 
	 * @param application The Spring application builder.
	 * @return The configured Spring application builder.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BankApplication.class);
	}

}
