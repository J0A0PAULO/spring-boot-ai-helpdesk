package br.com.helpDesk.chamados;


import br.com.helpDesk.chamados.dto.ChamadoReponse;
import br.com.helpDesk.chamados.dto.StatusRquest;
import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.chamados.mapper.ChamadoMapper;
import br.com.helpDesk.chamados.repository.ChamadoRepository;
import br.com.helpDesk.chamados.service.AtendenteChamadoService;
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
public class AtendenteServiceTest {

    @InjectMocks
    private AtendenteChamadoService atendenteChamadoService;

    @Mock
    private ChamadoMapper chamadoMapper;

    @Mock
    private ChamadoRepository chamadoRepository;

    @Test
    public void alterarStatus() {

        Chamado chamado = new Chamado();
        chamado.setStatus("FINALIZADO");
        chamado.setId(1L);

        StatusRquest statusRequest = new StatusRquest();
        statusRequest.setStatus("FINALIZADO");

        Mockito.when(chamadoRepository.findById(chamado.getId())).thenReturn(Optional.of(chamado));
        Mockito.when(chamadoRepository.save(Mockito.any(Chamado.class))).thenReturn(chamado);


        atendenteChamadoService.alterarStatusDoPedido(chamado.getId(), statusRequest);

        Assertions.assertEquals("FINALIZADO", chamado.getStatus());
        

    }

    @Test
    public void listarChamados() {

      Chamado chamado = new Chamado();
      chamado.setId(1L);
      chamado.setStatus("ABERTO");

      ChamadoReponse chamadoReponse = new ChamadoReponse();
      chamadoReponse.setStatus("ABERTO");

      Mockito.when(chamadoRepository.findAll()).thenReturn(List.of(chamado));
      Mockito.when(chamadoMapper.toDTO(Mockito.any(Chamado.class))).thenReturn(chamadoReponse);

        List<ChamadoReponse> resultado = atendenteChamadoService.exibirTodosChamados();

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("ABERTO", resultado.get(0).getStatus());

    }


    @Test
    public void exibirPorId() {

        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setStatus("ABERTO");

        ChamadoReponse chamadoReponse = new ChamadoReponse();
        chamadoReponse.setStatus("ABERTO");



        Mockito.when(chamadoRepository.findById(chamado.getId())).thenReturn(Optional.of(chamado));
        Mockito.when(chamadoMapper.toDTO(Mockito.any(Chamado.class))).thenReturn(chamadoReponse);

        ChamadoReponse resultado = atendenteChamadoService.exibirPorId(chamado.getId());

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("ABERTO", resultado.getStatus());

    }
    @Test
    public void alterarStatusDoPedido() {

        Chamado chamado = new Chamado();
        chamado.setId(1L);

        Mockito.when(chamadoRepository.findById(chamado.getId())).thenReturn(Optional.of(chamado));

        atendenteChamadoService.deletarChamado(chamado.getId());

        Mockito.verify(chamadoRepository, Mockito.times(1)).delete(chamado);

    }

}
