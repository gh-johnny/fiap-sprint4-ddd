package com.dasa.keepinventory.application.cqs.queries.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.Query;
import com.dasa.keepinventory.domain.entities.Almoxarifado;

public class ObterAlmoxarifadoPorIdQuery implements Query<Almoxarifado> {
    private final Long id;

    public ObterAlmoxarifadoPorIdQuery(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
}
