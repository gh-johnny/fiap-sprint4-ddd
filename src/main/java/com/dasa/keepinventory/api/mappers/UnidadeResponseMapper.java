package com.dasa.keepinventory.api.mappers;

import com.dasa.keepinventory.api.dto.response.UnidadeResponse;
import com.dasa.keepinventory.domain.entities.Unidade;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnidadeResponseMapper {
    
    public UnidadeResponse toResponse(Unidade unidade) {
        return new UnidadeResponse(
            unidade.getId(),
            unidade.getInformacao().getNome(),
            unidade.getInformacao().getSetor()
        );
    }

    public List<UnidadeResponse> toResponseList(List<Unidade> unidades) {
        return unidades.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
}
