
package com.dasa.keepinventory.domain.specifications.movimentacao;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.specifications.base.Specification;

public class EhDoTipoSpec implements Specification<MovimentacaoEstoque> {
    private final MovimentacaoEstoque.TipoMovimentacao tipo;

    public EhDoTipoSpec(MovimentacaoEstoque.TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean satisfeitoPor(MovimentacaoEstoque movimentacao) {
        return movimentacao.getTipo() == tipo;
    }
}
