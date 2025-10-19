package com.dasa.keepinventory.domain.builders;

import com.dasa.keepinventory.domain.builders.base.Builder;
import com.dasa.keepinventory.domain.entities.Almoxarifado;
import com.dasa.keepinventory.domain.valueobjects.Localizacao;

public class AlmoxarifadoBuilder implements Builder<Almoxarifado> {
    private Long id;
    private String localizacao;

    public AlmoxarifadoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AlmoxarifadoBuilder localizacao(String localizacao) {
        this.localizacao = localizacao;
        return this;
    }

    @Override
    public Almoxarifado build() {
        Localizacao loc = Localizacao.of(localizacao);
        
        if (id == null) {
            return Almoxarifado.criar(loc);
        }
        return Almoxarifado.reconstituir(id, loc);
    }
}
