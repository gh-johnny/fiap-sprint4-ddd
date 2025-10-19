package com.dasa.keepinventory.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "unidade")
public class UnidadeJpaEntity extends BaseJpaEntity {
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String setor;

    public UnidadeJpaEntity() {}

    public UnidadeJpaEntity(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}
