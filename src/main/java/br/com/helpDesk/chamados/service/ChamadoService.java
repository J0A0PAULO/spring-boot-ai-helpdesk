package br.com.helpDesk.chamados.service;

import br.com.helpDesk.chamados.dto.ChamadoReponse;
import br.com.helpDesk.chamados.dto.ChamadoRequest;
import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.chamados.mapper.ChamadoMapper;
import br.com.helpDesk.chamados.repository.ChamadoRepository;
import br.com.helpDesk.exceptions.NotFound;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {


    private final UsuarioRepository usuarioRepository;
    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

    public ChamadoService(UsuarioRepository usuarioRepository, ChamadoRepository chamadoRepository, ChamadoMapper chamadoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.chamadoRepository = chamadoRepository;
        this.chamadoMapper = chamadoMapper;
    }

    @Transactional
    public ChamadoReponse criarChamado(Long id, ChamadoRequest chamadoRequest) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFound("usuario com " + id + " não encontrado"));

        Chamado entity = chamadoMapper.toEntity(chamadoRequest);
        entity.setStatus("EM_ABERTO");
        entity.setUsuario(usuario);

        Chamado save = chamadoRepository.save(entity);

        ChamadoReponse dto = chamadoMapper.toDTO(save);

        return dto;

    }

    @Transactional
    public List<ChamadoReponse> exibirChamados(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFound("usuario não encontrado"));

        List<ChamadoReponse> list = chamadoRepository.findByUsuarioId(usuario.getId())
                    .stream().map(chamados -> chamadoMapper.toDTO(chamados)).toList();

        return list;

    }

    @Transactional
    public ChamadoReponse exibirChamadoPorId(Long usuarioId, Long chamadoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new NotFound("Usuario com" + usuarioId + " não encontrado"));

        Chamado chamado = chamadoRepository.findById(chamadoId).orElseThrow(() -> new NotFound("chamado com" + chamadoId + " não encontrado"));

        if (!chamado.getUsuario().getId().equals(usuario.getId())) {
            throw  new NotFound("Chamado não pertence a esse usuario");
        }

        ChamadoReponse dto = chamadoMapper.toDTO(chamado);

        return dto;

    }


    public ChamadoReponse alterarChamado(Long idUsuario, Long id, ChamadoRequest request){

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFound("usuario não encontrado"));

        Chamado chamado = chamadoRepository.findById(id).orElseThrow(() -> new NotFound("chamado com " + id + " não encontrado"));

        if (!chamado.getUsuario().getId().equals(usuario.getId())){
            throw  new NotFound("Chamado não pertence a esse usuario");
        };

        chamadoMapper.toEntityConvert(request, chamado);

        chamadoRepository.save(chamado);

        ChamadoReponse dto = chamadoMapper.toDTO(chamado);

        return dto;

    }

    public void deletarChamado(Long idUsuario, Long id) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFound("usuario não encontrado"));

        Chamado chamado = chamadoRepository.findById(id).orElseThrow(() -> new NotFound("usuario não encontrado"));

        if (!chamado.getUsuario().getId().equals(usuario.getId())) {
            throw  new NotFound("Chamado não pertence a esse usuario");
        }

        chamadoRepository.deleteById(id);
    }


}
