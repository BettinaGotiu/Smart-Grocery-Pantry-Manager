package com.smartgrocery.smart_grocery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // dezactivează CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // permite toate request-urile
                )
                .formLogin(form -> form.disable()) // 🚀 dezactivează login form
                .httpBasic(basic -> basic.disable()); // 🚀 dezactivează basic auth

        return http.build();
    }
}