package br.com.helpDesk.mensagens.repository;


import br.com.helpDesk.mensagens.entity.Mensagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MensagensRepository extends JpaRepository<Mensagens, Long> {

    List<Mensagens> findByUsuario(Long id);



}
