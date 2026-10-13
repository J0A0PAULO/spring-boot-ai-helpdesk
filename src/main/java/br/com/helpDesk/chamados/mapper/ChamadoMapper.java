package br.com.helpDesk.chamados.mapper;

import br.com.helpDesk.chamados.dto.ChamadoReponse;
import br.com.helpDesk.chamados.dto.ChamadoRequest;
import br.com.helpDesk.chamados.entity.Chamado;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {

    ChamadoRequest toEntity(Chamado chamado);

    Chamado toDTO(ChamadoReponse chamadoReponse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntityConvert(ChamadoRequest chamadoRequest, @MappingTarget Chamado chamado);


}
