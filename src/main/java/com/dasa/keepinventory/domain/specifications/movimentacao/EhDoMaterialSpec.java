package com.dasa.keepinventory.domain.specifications.movimentacao;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.specifications.base.Specification;

public class EhDoMaterialSpec implements Specification<MovimentacaoEstoque> {
    private final Long materialId;

    public EhDoMaterialSpec(Long materialId) {
        this.materialId = materialId;
    }

    @Override
    public boolean satisfeitoPor(MovimentacaoEstoque movimentacao) {
        return movimentacao.getMaterial().getId().equals(materialId);
    }
}
