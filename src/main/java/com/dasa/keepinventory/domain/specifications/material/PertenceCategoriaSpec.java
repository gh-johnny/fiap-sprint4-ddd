package com.dasa.keepinventory.domain.specifications.material;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.specifications.base.Specification;

public class PertenceCategoriaSpec implements Specification<Material> {
    private final String categoria;

    public PertenceCategoriaSpec(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean satisfeitoPor(Material material) {
        if (material.getInformacao().getCategoria() == null) {
            return false;
        }
        return material.getInformacao().getCategoria().equalsIgnoreCase(categoria);
    }
}
