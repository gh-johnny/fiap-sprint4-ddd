package com.dasa.keepinventory.application.cqs.commands.consumo;

import com.dasa.keepinventory.application.cqs.base.Command;

public class RegistrarConsumoCommand implements Command {
    private final Long idMaterial;
    private final Long idUnidade;
    private final Long idResponsavel;
    private final Double quantidade;
    private final String observacao;

    public RegistrarConsumoCommand(Long idMaterial, Long idUnidade, Long idResponsavel,
                                  Double quantidade, String observacao) {
        this.idMaterial = idMaterial;
        this.idUnidade = idUnidade;
        this.idResponsavel = idResponsavel;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public Long getIdMaterial() { return idMaterial; }
    public Long getIdUnidade() { return idUnidade; }
    public Long getIdResponsavel() { return idResponsavel; }
    public Double getQuantidade() { return quantidade; }
    public String getObservacao() { return observacao; }
}
