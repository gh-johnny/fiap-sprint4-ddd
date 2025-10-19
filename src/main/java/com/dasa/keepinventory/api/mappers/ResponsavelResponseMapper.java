package com.dasa.keepinventory.api.mappers;

import com.dasa.keepinventory.api.dto.response.ResponsavelResponse;
import com.dasa.keepinventory.domain.entities.Responsavel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponsavelResponseMapper {
    
    public ResponsavelResponse toResponse(Responsavel responsavel) {
        return new ResponsavelResponse(
            responsavel.getId(),
            responsavel.getInformacao().getNome(),
            responsavel.getInformacao().getCargo()
        );
    }

    public List<ResponsavelResponse> toResponseList(List<Responsavel> responsaveis) {
        return responsaveis.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
}
