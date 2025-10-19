package com.dasa.keepinventory.domain.builders;

import com.dasa.keepinventory.domain.builders.base.Builder;
import com.dasa.keepinventory.domain.entities.Responsavel;
import com.dasa.keepinventory.domain.valueobjects.InformacaoPessoa;

public class ResponsavelBuilder implements Builder<Responsavel> {
    private Long id;
    private String nome;
    private String cargo;

    public ResponsavelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ResponsavelBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ResponsavelBuilder cargo(String cargo) {
        this.cargo = cargo;
        return this;
    }

    @Override
    public Responsavel build() {
        InformacaoPessoa info = InformacaoPessoa.of(nome, cargo);
        
        if (id == null) {
            return Responsavel.criar(info);
        }
        return Responsavel.reconstituir(id, info);
    }
}
