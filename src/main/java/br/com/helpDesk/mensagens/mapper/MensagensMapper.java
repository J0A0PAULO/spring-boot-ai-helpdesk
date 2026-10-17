package br.com.helpDesk.mensagens.mapper;

import br.com.helpDesk.mensagens.dto.MensagensResponse;
import br.com.helpDesk.mensagens.entity.Mensagens;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MensagensMapper {

    @Mapping(source = "chamado.id", target = "chamadoId")
    MensagensResponse toDTO(Mensagens mensagens);

}
