package br.com.helpDesk.usuarios;

import br.com.helpDesk.config.TokenConfig;
import br.com.helpDesk.usuarios.dto.CadastroLogin;
import br.com.helpDesk.usuarios.dto.LoginCadastro;
import br.com.helpDesk.usuarios.dto.UsuarioToken;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.service.UsuarioService;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
public class usuariosServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    Authentication authentication;

    @Mock
    TokenConfig tokenConfig;


    @Test
    void criarUsuarioClient() {

        CadastroLogin usuarioDTO = new CadastroLogin();
        usuarioDTO.setNome("joao");
        usuarioDTO.setEmail("joao@gmail.com");
        usuarioDTO.setSenha("123");

        Mockito.when(passwordEncoder.encode("123")).thenReturn("senha-criptografada");

        Usuario usuario = new Usuario();
        usuario.setNome("joao");
        usuario.setEmail("joao@gmail.com");
        usuario.setSenha("123");

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        String string = usuarioService.cadastrarClient(usuarioDTO);

    }

    @Test
    void loginUsuario() {

        Authentication authentication = Mockito.mock(Authentication.class);

        LoginCadastro loginCadastro = new LoginCadastro();
        loginCadastro.setEmail("joao@gmail.com");
        loginCadastro.setSenha("123");

        Usuario usuario = new Usuario();
        usuario.setEmail("joao@gmail.com");
        usuario.setSenha("123");

        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        Mockito.when(authentication.getPrincipal()).thenReturn(usuario);

        Mockito.when(tokenConfig.generateToken(usuario)).thenReturn(String.valueOf("aaaaaa121212"));

        UsuarioToken resultado = usuarioService.Login(loginCadastro);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("aaaaaa121212", resultado.getToken());

    }


}
