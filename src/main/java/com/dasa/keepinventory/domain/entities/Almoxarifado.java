package com.dasa.keepinventory.domain.entities;

import com.dasa.keepinventory.domain.builders.base.Buildable;
import com.dasa.keepinventory.domain.builders.AlmoxarifadoBuilder;
import com.dasa.keepinventory.domain.valueobjects.Localizacao;

/**
 * Entidade Almoxarifado refatorada
 */
public class Almoxarifado implements Buildable<Almoxarifado> {
    private Long id;
    private Localizacao localizacao;

    // Construtor privado
    private Almoxarifado(Long id, Localizacao localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    // Static factory methods
    public static Almoxarifado criar(Localizacao localizacao) {
        return new Almoxarifado(null, localizacao);
    }

    public static Almoxarifado reconstituir(Long id, Localizacao localizacao) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo ao reconstituir");
        }
        return new Almoxarifado(id, localizacao);
    }

    public static AlmoxarifadoBuilder builder() {
        return new AlmoxarifadoBuilder();
    }

    public void atualizarLocalizacao(Localizacao novaLocalizacao) {
        this.localizacao = novaLocalizacao;
    }

    // Getters
    public Long getId() { return id; }
    public Localizacao getLocalizacao() { return localizacao; }
    public void setId(Long id) { this.id = id; }
}
