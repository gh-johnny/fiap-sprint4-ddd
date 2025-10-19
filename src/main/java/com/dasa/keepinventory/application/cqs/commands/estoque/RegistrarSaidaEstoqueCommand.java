package com.dasa.keepinventory.application.cqs.commands.estoque;

import com.dasa.keepinventory.application.cqs.base.Command;

public class RegistrarSaidaEstoqueCommand implements Command {
    private final Long idMaterial;
    private final Long idAlmoxarifado;
    private final Long idResponsavel;
    private final Double quantidade;

    public RegistrarSaidaEstoqueCommand(Long idMaterial, Long idAlmoxarifado, 
                                       Long idResponsavel, Double quantidade) {
        this.idMaterial = idMaterial;
        this.idAlmoxarifado = idAlmoxarifado;
        this.idResponsavel = idResponsavel;
        this.quantidade = quantidade;
    }

    public Long getIdMaterial() { return idMaterial; }
    public Long getIdAlmoxarifado() { return idAlmoxarifado; }
    public Long getIdResponsavel() { return idResponsavel; }
    public Double getQuantidade() { return quantidade; }
}
