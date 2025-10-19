package com.dasa.keepinventory.application.cqs.commands.material;

import com.dasa.keepinventory.application.cqs.base.Command;

public class CriarMaterialCommand implements Command {
    private final String nome;
    private final String categoria;
    private final String unidadeMedida;
    private final Double quantidadeInicial;

    public CriarMaterialCommand(String nome, String categoria, 
                               String unidadeMedida, Double quantidadeInicial) {
        this.nome = nome;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeInicial = quantidadeInicial;
    }

    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public Double getQuantidadeInicial() { return quantidadeInicial; }
}
