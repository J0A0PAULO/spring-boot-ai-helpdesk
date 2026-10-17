package br.com.helpDesk.Mensagens;

import br.com.helpDesk.exceptions.NotFound;
import br.com.helpDesk.mensagens.dto.MensagensResponse;
import br.com.helpDesk.mensagens.entity.Mensagens;
import br.com.helpDesk.mensagens.mapper.MensagensMapper;
import br.com.helpDesk.mensagens.repository.MensagensRepository;
import br.com.helpDesk.mensagens.service.MensagemService;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MensagensServiceTest {

    @InjectMocks
    private MensagemService mensagemService;

    @Mock
    private MensagensMapper mensagensMapper;

    @Mock
    private MensagensRepository mensagensRepository;

    @Mock
    private UsuarioRepository usuarioRepository;


    @Test
    public void listarMensagens() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Mensagens mensagens = new Mensagens();
        mensagens.setId(1L);

        MensagensResponse mensagensResponse = new MensagensResponse();
        mensagensResponse.setId(1L);

        Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        Mockito.when(mensagensRepository.findByUsuarioId(usuario.getId())).thenReturn(List.of(mensagens));
        Mockito.when(mensagensMapper.toDTO(Mockito.any(Mensagens.class))).thenReturn(mensagensResponse);

        List<MensagensResponse> resultado = mensagemService.listarMensagens(usuario.getId());

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1L, resultado.get(0).getId());

        Mockito.verify(mensagensRepository, Mockito.times(1)).findByUsuarioId(usuario.getId());

    }

    @Test
    public void listarMensagensPorId() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Mensagens mensagens = new Mensagens();
        mensagens.setId(1L);
        mensagens.setUsuario(usuario);

        MensagensResponse mensagensResponse = new MensagensResponse();
        mensagensResponse.setId(1L);

        Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        Mockito.when(mensagensRepository.findById(mensagens.getId())).thenReturn(Optional.of(mensagens));
        Mockito.when(mensagensMapper.toDTO(Mockito.any(Mensagens.class))).thenReturn(mensagensResponse);

        MensagensResponse resultado = mensagemService.listarMensagenPorId(usuario.getId(), mensagens.getId());

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1L, resultado.getId());

        Mockito.verify(mensagensRepository, Mockito.times(1)).findById(mensagens.getId());

    }

}
