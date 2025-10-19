package com.dasa.keepinventory.domain.specifications.material;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;

public class EstaCriticamenteBaixoSpec implements Specification<Material> {
    private final Quantidade nivelCritico;

    public EstaCriticamenteBaixoSpec(Quantidade nivelCritico) {
        this.nivelCritico = nivelCritico;
    }

    @Override
    public boolean satisfeitoPor(Material material) {
        return material.getQuantidadeTotal().menorQue(nivelCritico);
    }
}
