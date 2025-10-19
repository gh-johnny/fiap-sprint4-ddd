package com.dasa.keepinventory.application.cqs.queries.material;

import com.dasa.keepinventory.application.cqs.base.Query;

public class ValidarConsumoMaterialQuery implements Query<ValidacaoConsumoResponse> {
    private final Long idMaterial;
    private final Double quantidade;
    private final Double nivelCritico;
    private final Double estoqueMinimo;

    public ValidarConsumoMaterialQuery(Long idMaterial, Double quantidade,
                                      Double nivelCritico, Double estoqueMinimo) {
        this.idMaterial = idMaterial;
        this.quantidade = quantidade;
        this.nivelCritico = nivelCritico;
        this.estoqueMinimo = estoqueMinimo;
    }

    public Long getIdMaterial() { return idMaterial; }
    public Double getQuantidade() { return quantidade; }
    public Double getNivelCritico() { return nivelCritico; }
    public Double getEstoqueMinimo() { return estoqueMinimo; }
}
