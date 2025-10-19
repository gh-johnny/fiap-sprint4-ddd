package com.dasa.keepinventory.domain.specifications.material;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;

public class PrecisaReposicaoSpec implements Specification<Material> {
    private final Quantidade estoqueMinimo;

    public PrecisaReposicaoSpec(Quantidade estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    @Override
    public boolean satisfeitoPor(Material material) {
        return material.getQuantidadeTotal().menorQue(estoqueMinimo);
    }
}
