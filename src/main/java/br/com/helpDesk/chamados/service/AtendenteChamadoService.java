package br.com.helpDesk.chamados.service;


import br.com.helpDesk.chamados.dto.ChamadoReponse;
import br.com.helpDesk.chamados.dto.StatusRequest;
import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.chamados.mapper.ChamadoMapper;
import br.com.helpDesk.chamados.repository.ChamadoRepository;
import br.com.helpDesk.exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendenteChamadoService {

    private  final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;


    public AtendenteChamadoService(ChamadoRepository chamadoRepository, ChamadoMapper chamadoMapper) {
        this.chamadoRepository = chamadoRepository;
        this.chamadoMapper = chamadoMapper;
    }

    public List<ChamadoReponse> exibirTodosChamados() {
        List<ChamadoReponse> list = chamadoRepository
                .findAll().stream().map(chamados -> chamadoMapper.toDTO(chamados)).toList();
        return list;
    }

    public ChamadoReponse exibirPorId(Long id) {
        ChamadoReponse chamadoReponse = chamadoRepository
                .findById(id).map(chamados -> chamadoMapper.toDTO(chamados))
                .orElseThrow(() -> new NotFound("usuario nao encontrado"));
        return chamadoReponse;

    }

    public String alterarStatusDoPedido(Long id, StatusRequest status) {

        Chamado chamado = chamadoRepository.findById(id).orElseThrow(() -> new NotFound("chamado nao encontrado"));
        chamado.setStatus(status.getStatus());

        chamadoRepository.save(chamado);

        return "Status Atualizado";

    }

    public void deletarChamado(Long id) {

        Chamado chamado = chamadoRepository.findById(id).orElseThrow(() -> new NotFound("chama nao encontrado"));

        chamadoRepository.delete(chamado);

    }

}
