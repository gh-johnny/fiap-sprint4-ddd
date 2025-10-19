package com.dasa.keepinventory.application.cqs.queries.material;

import com.dasa.keepinventory.application.cqs.base.Query;
import com.dasa.keepinventory.domain.entities.Material;

import java.util.List;

public class ListarMateriaisCriticosQuery implements Query<List<Material>> {
    private final Double nivelCritico;

    public ListarMateriaisCriticosQuery(Double nivelCritico) {
        this.nivelCritico = nivelCritico;
    }

    public Double getNivelCritico() { return nivelCritico; }
}
