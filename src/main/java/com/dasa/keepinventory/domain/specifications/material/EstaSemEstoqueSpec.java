package com.dasa.keepinventory.domain.specifications.material;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.specifications.base.Specification;

public class EstaSemEstoqueSpec implements Specification<Material> {

    @Override
    public boolean satisfeitoPor(Material material) {
        return material.getQuantidadeTotal().getValor() == 0.0;
    }
}
