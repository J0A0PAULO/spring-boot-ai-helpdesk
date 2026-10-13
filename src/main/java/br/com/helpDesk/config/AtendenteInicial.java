package br.com.helpDesk.config;

import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.enums.Roles;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AtendenteInicial {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AtendenteInicial(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public CommandLineRunner iniciarUsuarioAtendente() {

        return args -> {

            if (usuarioRepository.findByEmail("atendente@gmail.com").isEmpty()) {

                Usuario novoAtendente = new Usuario();
                novoAtendente.setNome("atendente");
                novoAtendente.setEmail("atendente@gmail.com");
                novoAtendente.setSenha(passwordEncoder.encode("123"));

                Set<Roles> roles = new HashSet<>();
                roles.add(Roles.ROLE_ATENDENTE);
                roles.add(Roles.ROLE_CLIENTE);

                novoAtendente.setRoles(roles);

                usuarioRepository.save(novoAtendente);
            }

        };
    }


}
