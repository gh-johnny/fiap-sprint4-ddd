package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "almoxarifado")
public class AlmoxarifadoJpaEntity extends BaseJpaEntity {
    
    @Column(nullable = false)
    private String localizacao;

    public AlmoxarifadoJpaEntity() {}

    public AlmoxarifadoJpaEntity(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
}
