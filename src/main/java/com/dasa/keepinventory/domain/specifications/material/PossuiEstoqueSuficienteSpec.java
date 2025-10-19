package com.dasa.keepinventory.domain.specifications.material;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;

public class PossuiEstoqueSuficienteSpec implements Specification<Material> {
    private final Quantidade quantidadeNecessaria;

    public PossuiEstoqueSuficienteSpec(Quantidade quantidadeNecessaria) {
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    @Override
    public boolean satisfeitoPor(Material material) {
        return material.getQuantidadeTotal().maiorOuIgualA(quantidadeNecessaria);
    }
}
