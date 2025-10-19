package com.dasa.keepinventory.application.cqs.queries.material;

import com.dasa.keepinventory.application.cqs.base.Query;
import com.dasa.keepinventory.domain.entities.Material;

public class ObterMaterialPorIdQuery implements Query<Material> {
    private final Long id;

    public ObterMaterialPorIdQuery(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
}
