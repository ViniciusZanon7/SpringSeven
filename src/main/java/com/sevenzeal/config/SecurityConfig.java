package com.sevenzeal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sevenzeal.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 🔥 DESLIGA TUDO QUE É STATEFUL
            .csrf(csrf -> csrf.disable())
            .httpBasic(http -> http.disable())
            .formLogin(form -> form.disable())

            // 🔥 ESSENCIAL PRA JWT
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🔥 evita 403 sem explicação
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )

            // 🔐 REGRAS
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**", "/usuarios/**").permitAll()
                .requestMatchers("/esteticas/**", "/servicos/**").hasAnyAuthority("ESTETICA", "ADMIN")
                .requestMatchers("/agendamentos/**").hasAnyAuthority("CLIENTE", "ESTETICA", "ADMIN")
                .requestMatchers("/estatisticas/**", "/notificacoes/**", "/historico-servicos/**").hasAnyAuthority("ADMIN", "ESTETICA")
                .anyRequest().authenticated()
            )

            // 🔥 JWT antes do padrão
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}