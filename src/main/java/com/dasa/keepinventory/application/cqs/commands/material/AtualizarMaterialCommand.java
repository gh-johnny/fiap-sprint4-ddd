package com.dasa.keepinventory.application.cqs.commands.material;

import com.dasa.keepinventory.application.cqs.base.Command;

public class AtualizarMaterialCommand implements Command {
    private final Long id;
    private final String nome;
    private final String categoria;
    private final String unidadeMedida;
    private final Double quantidadeTotal;

    public AtualizarMaterialCommand(Long id, String nome, String categoria, 
                                   String unidadeMedida, Double quantidadeTotal) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeTotal = quantidadeTotal;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public Double getQuantidadeTotal() { return quantidadeTotal; }
}
