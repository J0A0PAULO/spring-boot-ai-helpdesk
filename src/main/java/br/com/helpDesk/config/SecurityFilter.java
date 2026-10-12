package br.com.helpDesk.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authorizaedHeader = request.getHeader("Authorization");

        if (Strings.isNotEmpty(authorizaedHeader) && authorizaedHeader.startsWith("Bearer ")) {
            String token = authorizaedHeader.substring("Bearer ".length());
            Optional<JWTUserData> optUsuario = tokenConfig.validationToken(token);
            if (optUsuario.isPresent()) {
                JWTUserData userData = optUsuario.get();
                List<SimpleGrantedAuthority> authorities = userData.getRoles().stream().map(roles -> new SimpleGrantedAuthority(roles.name())).toList();

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userData, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        };

        filterChain.doFilter(request, response);

    }

}
