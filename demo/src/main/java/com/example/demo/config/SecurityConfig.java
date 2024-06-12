package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .requestMatchers("/jokes", "/jokes/top", "/jokes/random", "/jokes/{id}", "/actuator/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/jokes").hasAuthority("joke:write")
                .requestMatchers(HttpMethod.PUT, "/jokes/{id}").hasAnyAuthority("joke:update", "joke:delete")
                .requestMatchers(HttpMethod.DELETE, "/jokes/{id}").hasAnyAuthority("joke:delete")
                .requestMatchers("/users/**").hasAuthority("user:manage")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
