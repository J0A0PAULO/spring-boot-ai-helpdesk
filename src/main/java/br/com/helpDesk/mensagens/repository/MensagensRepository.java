package br.com.helpDesk.mensagens.repository;


import br.com.helpDesk.mensagens.entity.Mensagens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagensRepository extends JpaRepository<Mensagens, Long> {



}
