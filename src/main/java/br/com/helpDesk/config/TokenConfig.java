package br.com.helpDesk.config;

import br.com.helpDesk.usuarios.Usuario;
import br.com.helpDesk.usuarios.enums.Roles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TokenConfig {

    @Value("${SPRING_SECURITY_SECRET:AAAAAA1212}")
    private String secret;

    public String generateToken(Usuario usuario) {


        Algorithm algorithm = Algorithm.HMAC256(secret);

        List<String> rolesNames =usuario.getRoles().stream().map(roles -> roles.name()).toList();

        return JWT.create()
                .withClaim("usuario_id", usuario.getId())
                .withClaim("roles", rolesNames)
                .withSubject(usuario.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(12000))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

        Optional<JWTUserData> validationToken(String token) {

            try {

                Algorithm algorithm = Algorithm.HMAC256(secret);

                DecodedJWT decode = JWT.require(algorithm).build().verify(token);

                Long id = decode.getClaim("usuario_id").asLong();
                String email= decode.getSubject();
                List<String> rolesStrings= decode.getClaim("roles").asList(String.class);

                if (id != null && email != null && !rolesStrings.isEmpty()) {

                    HashSet<Roles> roles = rolesStrings.stream()
                            .map(rolesLista -> Roles.valueOf(rolesLista))
                            .collect(Collectors.toCollection(() -> new HashSet<>()));

                    JWTUserData userData = new JWTUserData(id, email, roles);
                    return Optional.of(userData);

                }

              return   Optional.empty();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }



}
