package br.com.helpDesk.chamados.repository;


import br.com.helpDesk.chamados.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChamadoRepository  extends JpaRepository<Chamado, Long> {

    List<Chamado> findByUsuarioId(Long id);

}
