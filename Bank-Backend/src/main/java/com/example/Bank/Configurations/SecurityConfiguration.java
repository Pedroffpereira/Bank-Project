package com.example.Bank.Configurations;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.Bank.Utilaties.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * Configuration of passwordDecodoer and Authentication system.
 * This class provides configuration for password encoding, authentication
 * manager, CORS, security filter chain,
 * JWT decoder and encoder, and JWT authentication converter.
 * 
 * @author Pedro Pereira
 * @version 1.0
 */

@Configuration
@EnableWebMvc
public class SecurityConfiguration {
    /**
     * The RSAKeyProperties containing the RSA key properties for JWT token encoding
     * and decoding.
     */
    private final RSAKeyProperties keys;

    /**
     * Constructs a new SecurityConfiguration with the specified RSA key properties.
     * 
     * @param keys The RSAKeyProperties containing RSA key configuration properties.
     */
    public SecurityConfiguration(RSAKeyProperties keys) {
        this.keys = keys;
    }

    /**
     * Provides a bean for encoding passwords using BCryptPasswordEncoder.
     *
     * @return The PasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a bean for creating an AuthenticationManager.
     *
     * @param detailsService The UserDetailsService implementation.
     * @return The AuthenticationManager bean.
     */
    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }

    /**
     * Provides a bean for configuring CORS (Cross-Origin Resource Sharing)
     * policies.
     *
     * @return The CorsConfigurationSource bean.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:80"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return configurationSource;
    }

    /**
     * Provides a bean for configuring the security filter chain.
     *
     * @param http The HttpSecurity object.
     * @return The SecurityFilterChain bean.
     * @throws Exception If an error occurs while configuring the filter chain.
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    // auth.requestMatchers("/api/v1/users/**").permitAll();
                    // auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    // auth.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/api/v1/users/**").permitAll();
                    auth.requestMatchers("/swagger-ui/**").permitAll();
                    auth.requestMatchers("/api-docs/**").permitAll();
                    auth.requestMatchers("/ws/**").permitAll();
                    auth.requestMatchers("/app/**").permitAll();
                    auth.anyRequest().authenticated();
                });

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    /**
     * Provides a bean for decoding JWT tokens.
     *
     * @return The JwtDecoder bean.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    /**
     * Provides a bean for encoding JWT tokens.
     *
     * @return The JwtEncoder bean.
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        RSAKey jwk = new RSAKey.Builder(this.keys.getPublicKey()).privateKey(this.keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    /**
     * Provides a bean for configuring JWT authentication.
     *
     * @return The JwtAuthenticationConverter bean.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}
