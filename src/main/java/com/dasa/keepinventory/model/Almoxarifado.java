package com.dasa.keepinventory.model;

import com.dasa.keepinventory.model.abstractions.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Almoxarifado extends BaseEntity {

    @Column(nullable = false)
    private String localizacao;

    public Almoxarifado() {}

    public Almoxarifado(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
}
