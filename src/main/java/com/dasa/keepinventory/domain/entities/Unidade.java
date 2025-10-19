
package com.dasa.keepinventory.domain.entities;

import com.dasa.keepinventory.domain.builders.base.Buildable;
import com.dasa.keepinventory.domain.builders.UnidadeBuilder;
import com.dasa.keepinventory.domain.valueobjects.InformacaoUnidade;

public class Unidade implements Buildable<Unidade> {
    private Long id;
    private InformacaoUnidade informacao;

    // Construtor privado
    private Unidade(Long id, InformacaoUnidade informacao) {
        this.id = id;
        this.informacao = informacao;
    }

    // Static factory methods
    public static Unidade criar(InformacaoUnidade informacao) {
        return new Unidade(null, informacao);
    }

    public static Unidade reconstituir(Long id, InformacaoUnidade informacao) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo ao reconstituir");
        }
        return new Unidade(id, informacao);
    }

    public static UnidadeBuilder builder() {
        return new UnidadeBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public InformacaoUnidade getInformacao() { return informacao; }
    public void setId(Long id) { this.id = id; }
}
