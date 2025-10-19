package com.dasa.keepinventory.domain.builders;

import com.dasa.keepinventory.domain.builders.base.Builder;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.valueobjects.*;

public class MaterialBuilder implements Builder<Material> {
    private Long id;
    private String nome;
    private String categoria;
    private String unidadeMedida;
    private Double quantidadeTotal = 0.0;

    public MaterialBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public MaterialBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public MaterialBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public MaterialBuilder unidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
        return this;
    }

    public MaterialBuilder quantidadeTotal(Double quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
        return this;
    }

    @Override
    public Material build() {
        InformacaoMaterial info = InformacaoMaterial.of(nome, categoria, unidadeMedida);
        Quantidade qtd = Quantidade.of(quantidadeTotal);
        
        if (id == null) {
            return Material.criar(info, qtd);
        }
        return Material.reconstituir(id, info, qtd);
    }
}
