package br.com.helpDesk.mensagens.mapper;

import br.com.helpDesk.mensagens.dto.MensagensResponse;
import br.com.helpDesk.mensagens.entity.Mensagens;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MensagensMapper {

    MensagensResponse toDTO(Mensagens mensagens);

}
