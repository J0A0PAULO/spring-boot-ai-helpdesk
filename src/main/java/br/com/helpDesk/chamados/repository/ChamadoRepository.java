package br.com.helpDesk.chamados.repository;


import br.com.helpDesk.chamados.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository  extends JpaRepository<Chamado, Long> {
}
