package com.dasa.keepinventory.api.mappers;

import com.dasa.keepinventory.api.dto.response.MaterialResponse;
import com.dasa.keepinventory.domain.entities.Material;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaterialResponseMapper {
    
    public MaterialResponse toResponse(Material material) {
        return new MaterialResponse(
            material.getId(),
            material.getInformacao().getNome(),
            material.getInformacao().getCategoria(),
            material.getInformacao().getUnidadeMedida().getValor(),
            material.getQuantidadeTotal().getValor()
        );
    }

    public List<MaterialResponse> toResponseList(List<Material> materiais) {
        return materiais.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
}
