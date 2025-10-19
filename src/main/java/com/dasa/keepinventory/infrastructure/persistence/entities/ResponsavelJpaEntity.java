package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "responsavel")
public class ResponsavelJpaEntity extends BaseJpaEntity {
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String cargo;

    public ResponsavelJpaEntity() {}

    public ResponsavelJpaEntity(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
