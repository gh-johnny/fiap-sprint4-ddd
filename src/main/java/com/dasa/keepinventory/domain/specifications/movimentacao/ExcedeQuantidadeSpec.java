
package com.dasa.keepinventory.domain.specifications.movimentacao;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;

public class ExcedeQuantidadeSpec implements Specification<MovimentacaoEstoque> {
    private final Quantidade limiar;

    public ExcedeQuantidadeSpec(Quantidade limiar) {
        this.limiar = limiar;
    }

    @Override
    public boolean satisfeitoPor(MovimentacaoEstoque movimentacao) {
        return movimentacao.getQuantidade().maiorOuIgualA(limiar);
    }
}
