package com.dasa.keepinventory.domain.specifications.movimentacao;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import java.time.LocalDateTime;

public class OcorreuNoPeriodoSpec implements Specification<MovimentacaoEstoque> {
    private final LocalDateTime dataInicio;
    private final LocalDateTime dataFim;

    public OcorreuNoPeriodoSpec(LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public boolean satisfeitoPor(MovimentacaoEstoque movimentacao) {
        LocalDateTime dataMovimentacao = movimentacao.getDataMovimentacao();
        return !dataMovimentacao.isBefore(dataInicio) && !dataMovimentacao.isAfter(dataFim);
    }
}
