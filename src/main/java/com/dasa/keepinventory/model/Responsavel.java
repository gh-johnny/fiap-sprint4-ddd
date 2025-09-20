package com.dasa.keepinventory.model;

import com.dasa.keepinventory.model.abstractions.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Responsavel extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column
    private String cargo;

    public Responsavel() {}
    public Responsavel(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
