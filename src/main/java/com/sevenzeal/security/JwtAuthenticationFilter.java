package com.sevenzeal.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sevenzeal.service.JwtService;
import com.sevenzeal.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // 🔓 Ignorar rotas públicas
        if (
             path.startsWith("/auth") ||
             path.startsWith("/usuarios") ||
             path.startsWith("/esteticas") ||
             path.startsWith("/servicos")
) {
    filterChain.doFilter(request, response);
    return;
}
        String authHeader = request.getHeader("Authorization");

        // 🔓 Se não tiver token, segue sem autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtService.extrairEmail(token);
            String tipoUsuario = jwtService.extrairTipoUsuario(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                userRepository.findByEmail(email).ifPresent(user -> {
                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority(tipoUsuario.toUpperCase());

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    null,
                                    List.of(authority)
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                });
            }

        } catch (Exception ex) {
            // 🔥 limpa contexto se token for inválido
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}