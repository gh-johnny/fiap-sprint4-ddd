package com.dasa.keepinventory.application.cqs.queries.relatorio;

import java.util.Map;

public class RelatorioStatusResponse {
    private final long totalMateriais;
    private final long materiaisCriticos;
    private final long materiaisBoasCondicoes;
    private final long materiaisDisponiveis;
    private final Map<String, Long> distribuicaoPorCategoria;

    public RelatorioStatusResponse(long totalMateriais, long materiaisCriticos,
                                  long materiaisBoasCondicoes, long materiaisDisponiveis,
                                  Map<String, Long> distribuicaoPorCategoria) {
        this.totalMateriais = totalMateriais;
        this.materiaisCriticos = materiaisCriticos;
        this.materiaisBoasCondicoes = materiaisBoasCondicoes;
        this.materiaisDisponiveis = materiaisDisponiveis;
        this.distribuicaoPorCategoria = distribuicaoPorCategoria;
    }

    public long getTotalMateriais() { return totalMateriais; }
    public long getMateriaisCriticos() { return materiaisCriticos; }
    public long getMateriaisBoasCondicoes() { return materiaisBoasCondicoes; }
    public long getMateriaisDisponiveis() { return materiaisDisponiveis; }
    public Map<String, Long> getDistribuicaoPorCategoria() { return distribuicaoPorCategoria; }
}
