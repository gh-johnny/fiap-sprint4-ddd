package com.dasa.keepinventory.api.mappers;

import com.dasa.keepinventory.api.dto.response.AlmoxarifadoResponse;
import com.dasa.keepinventory.domain.entities.Almoxarifado;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlmoxarifadoResponseMapper {
    
    public AlmoxarifadoResponse toResponse(Almoxarifado almoxarifado) {
        return new AlmoxarifadoResponse(
            almoxarifado.getId(),
            almoxarifado.getLocalizacao().getValor()
        );
    }

    public List<AlmoxarifadoResponse> toResponseList(List<Almoxarifado> almoxarifados) {
        return almoxarifados.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }
}
