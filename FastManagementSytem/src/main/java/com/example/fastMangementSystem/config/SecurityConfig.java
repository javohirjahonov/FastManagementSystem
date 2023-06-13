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
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final String[] COURSE_LINKS = new String[]{"/course/v1/add",
            "/course/v1/delete", "/course/v1/{courseId}/update"
    };

    private final String[] GROUP_LINKS = new String[]{
            "/api/v1/group/add", "/api/v1/group/delete", "/api/v1/group/{groupId}/update"
    };

    private final String[] LESSON_LINKS = new String[]{
            "/lesson/v1/add", "/lesson/v1/delete", "/lesson/v1/get-user-courses", "/lesson/v1/{lessonId}/update"
    };

    private final String[] MODULE_LINKS = new String[]{
            "/module/v1/add", "/module/v1/delete", "/module/v1/get-all", "/module/v1/{moduleId}/update"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((authorizer) -> {
                    authorizer
                            .requestMatchers("/api/v1/auth/**")
                            .permitAll()
                            .requestMatchers(COURSE_LINKS).hasRole("ADMIN")
                            .requestMatchers(LESSON_LINKS).hasRole("ADMIN")
                            .requestMatchers(GROUP_LINKS).hasRole("ADMIN")
                            .requestMatchers(MODULE_LINKS).hasRole("ADMIN")
                            .requestMatchers("/lesson/v1/get-user-courses", "/lesson/v1/get-user-courses").hasRole("CLIENT")
                            .anyRequest()
                            .authenticated();
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
