package com.example.fastMangementSystem.config;

import com.example.fastMangementSystem.filter.JwtTokenFilter;
import com.example.fastMangementSystem.service.auth.AuthService;
import com.example.fastMangementSystem.service.auth.AuthenticationService;
import com.example.fastMangementSystem.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((authorizer) -> {
                    authorizer
                            .requestMatchers("/api/v1/auth/**").permitAll();
                })
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtTokenFilter(authenticationService, jwtService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
