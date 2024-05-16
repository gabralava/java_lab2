package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/jokes").permitAll() // Разрешение доступа к шуткам без авторизации
                        .requestMatchers("/jokes/top").permitAll() 
                        .requestMatchers("/jokes/random").permitAll()
                        .requestMatchers("/jokes/{id}").permitAll()
                        .requestMatchers("/register").permitAll() // Разрешение регистрации
                        .anyRequest().authenticated() //  Все остальные запросы требуют авторизацию
                )
                .formLogin(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable); // Используем Basic Authentication
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}