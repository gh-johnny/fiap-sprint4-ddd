package com.dasa.keepinventory.domain.builders;

import com.dasa.keepinventory.domain.builders.base.Builder;
import com.dasa.keepinventory.domain.entities.Unidade;
import com.dasa.keepinventory.domain.valueobjects.InformacaoUnidade;

public class UnidadeBuilder implements Builder<Unidade> {
    private Long id;
    private String nome;
    private String setor;

    public UnidadeBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UnidadeBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UnidadeBuilder setor(String setor) {
        this.setor = setor;
        return this;
    }

    @Override
    public Unidade build() {
        InformacaoUnidade info = InformacaoUnidade.of(nome, setor);
        
        if (id == null) {
            return Unidade.criar(info);
        }
        return Unidade.reconstituir(id, info);
    }
}
