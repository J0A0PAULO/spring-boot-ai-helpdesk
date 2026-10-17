package br.com.helpDesk.chamados;

import br.com.helpDesk.chamados.dto.ChamadoReponse;
import br.com.helpDesk.chamados.dto.ChamadoRequest;
import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.chamados.mapper.ChamadoMapper;
import br.com.helpDesk.chamados.repository.ChamadoRepository;
import br.com.helpDesk.chamados.service.ClientChamadoService;
import br.com.helpDesk.exceptions.NotFound;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {


    @InjectMocks
    private ClientChamadoService clientChamadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private ChamadoMapper chamadoMapper;


    @Test
    public void criarChamado() {

        Long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setStatus("EM_ABERTO");

        ChamadoRequest chamadoRequest = new ChamadoRequest();

        ChamadoReponse chamadoReponse = new ChamadoReponse();
        chamadoReponse.setStatus("EM_ABERTO");

        Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        Mockito.when(chamadoMapper.toEntity(Mockito.any(ChamadoRequest.class))).thenReturn(chamado);
        Mockito.when(chamadoRepository.save(chamado)).thenReturn(chamado);
        Mockito.when(chamadoMapper.toDTO(Mockito.any(Chamado.class))).thenReturn(chamadoReponse);

        ChamadoReponse resultado = clientChamadoService.criarChamado(id, chamadoRequest);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("EM_ABERTO", resultado.getStatus());

    }

    @Test
    public void exibirChamados() {

        Long id =1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);

        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setStatus("FINALIZADO");
        chamado.setUsuario(usuario);

        ChamadoReponse chamadoReponse = new ChamadoReponse();
        chamadoReponse.setStatus("FINALIZADO");

        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        Mockito.when(chamadoRepository.findByUsuarioId(usuario.getId())).thenReturn(List.of(chamado));
        Mockito.when(chamadoMapper.toDTO(Mockito.any(Chamado.class))).thenReturn(chamadoReponse);

        List<ChamadoReponse> resultado = clientChamadoService.exibirChamados(id);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("FINALIZADO", resultado.get(0).getStatus());
    }

    @Test
    public void exibirPedidoPorId() {

        Long usuarioId =1L;

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setStatus("FINALIZADO");
        chamado.setUsuario(usuario);

        ChamadoReponse chamadoReponse = new ChamadoReponse();
        chamadoReponse.setStatus("FINALIZADO");

        Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        Mockito.when(chamadoRepository.findById(chamado.getUsuario().getId())).thenReturn(Optional.of(chamado));
        Mockito.when(chamadoMapper.toDTO(Mockito.any(Chamado.class))).thenReturn(chamadoReponse);

        ChamadoReponse resultado = clientChamadoService.exibirChamadoPorId(usuarioId, usuario.getId());

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("FINALIZADO", resultado.getStatus());
    }

    @Test
    public void alterarChamado() {

        Long usuarioId =1L;

        Long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setStatus("FINALIZADO");
        chamado.setUsuario(usuario);

        ChamadoReponse chamadoReponse = new ChamadoReponse();
        chamadoReponse.setStatus("FINALIZADO");

        ChamadoRequest chamadoRequest = new ChamadoRequest();


        Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        Mockito.when(chamadoRepository.findById(chamado.getUsuario().getId())).thenReturn(Optional.of(chamado));


        Mockito.when(chamadoRepository.save(Mockito.any(Chamado.class))).thenReturn(chamado);
        Mockito.when(chamadoMapper.toDTO(Mockito.any(Chamado.class))).thenReturn(chamadoReponse);

        ChamadoReponse resultado = clientChamadoService.alterarChamado(usuarioId, 1L, chamadoRequest);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("FINALIZADO", resultado.getStatus());

        Mockito.verify(chamadoMapper, Mockito.times(1)).toEntityConvert(chamadoRequest, chamado);

    }


    @Test
    public void deletarChamado() {

        Long usuarioId = 1L;
        Long chamadoId = 10L;

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        Chamado chamado = new Chamado();
        chamado.setId(chamadoId);
        chamado.setStatus("FINALIZADO");
        chamado.setUsuario(usuario);

        Mockito.when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        Mockito.when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.of(chamado));

        clientChamadoService.deletarChamado(usuarioId, chamadoId);

        Mockito.verify(chamadoRepository, Mockito.times(1)).deleteById(chamadoId);
    }


}
