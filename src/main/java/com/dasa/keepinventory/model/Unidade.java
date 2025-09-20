package com.dasa.keepinventory.model;

import com.dasa.keepinventory.model.abstractions.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Unidade extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column
    private String setor;

    public Unidade() {}
    public Unidade(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}
