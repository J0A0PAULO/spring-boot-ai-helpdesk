package br.com.helpDesk.chamados.service;

import br.com.helpDesk.chamados.repository.ChamadoRepository;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService {


    private final UsuarioRepository usuarioRepository;
    private final ChamadoRepository chamadoRepository;

    public ChamadoService(UsuarioRepository usuarioRepository, ChamadoRepository chamadoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.chamadoRepository = chamadoRepository;
    }




}
