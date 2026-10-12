package br.com.helpDesk.usuarios.service;

import br.com.helpDesk.config.TokenConfig;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.dto.LoginCadastro;
import br.com.helpDesk.usuarios.dto.CadastroLogin;
import br.com.helpDesk.usuarios.dto.UsuarioToken;
import br.com.helpDesk.usuarios.enums.Roles;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @Transactional
    public String cadastrarClient(CadastroLogin request) {

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        HashSet<Roles> roles = new HashSet<>();
        roles.add(Roles.ROLE_CLIENTE);
        usuario.setRoles(roles);

         usuarioRepository.save(usuario);

        return "Usuario cadastrado com sucesso";

    }

    @Transactional
    public String cadastrarAtendente(CadastroLogin request) {

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));

        HashSet<Roles> roles = new HashSet<>();
        roles.add(Roles.ROLE_ATENDENTE);
        usuario.setRoles(roles);

        usuarioRepository.save(usuario);

        return "Usuario cadastrado com sucesso";
    }


    @Transactional
    public UsuarioToken Login(LoginCadastro loginCadastro) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(loginCadastro.getEmail(), loginCadastro.getSenha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);

        UsuarioToken usuarioToken = new UsuarioToken();
        usuarioToken.setToken(token);
        return usuarioToken;

    }

}
