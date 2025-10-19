
package com.dasa.keepinventory.domain.entities;

import com.dasa.keepinventory.domain.builders.base.Buildable;
import com.dasa.keepinventory.domain.builders.ResponsavelBuilder;
import com.dasa.keepinventory.domain.valueobjects.InformacaoPessoa;

public class Responsavel implements Buildable<Responsavel> {
    private Long id;
    private InformacaoPessoa informacao;

    // Construtor privado
    private Responsavel(Long id, InformacaoPessoa informacao) {
        this.id = id;
        this.informacao = informacao;
    }

    // Static factory methods
    public static Responsavel criar(InformacaoPessoa informacao) {
        return new Responsavel(null, informacao);
    }

    public static Responsavel reconstituir(Long id, InformacaoPessoa informacao) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo ao reconstituir");
        }
        return new Responsavel(id, informacao);
    }

    public static ResponsavelBuilder builder() {
        return new ResponsavelBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public InformacaoPessoa getInformacao() { return informacao; }
    public void setId(Long id) { this.id = id; }
}
