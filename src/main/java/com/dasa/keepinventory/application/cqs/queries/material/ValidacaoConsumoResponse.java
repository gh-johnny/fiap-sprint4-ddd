package com.dasa.keepinventory.application.cqs.queries.material;

public class ValidacaoConsumoResponse {
    private final boolean podeConsumir;
    private final boolean podeConsumirComSeguranca;
    private final boolean estaDisponivel;
    private final boolean estaEmBoasCondicoes;
    private final Double estoqueAtual;

    public ValidacaoConsumoResponse(boolean podeConsumir, boolean podeConsumirComSeguranca,
                                   boolean estaDisponivel, boolean estaEmBoasCondicoes,
                                   Double estoqueAtual) {
        this.podeConsumir = podeConsumir;
        this.podeConsumirComSeguranca = podeConsumirComSeguranca;
        this.estaDisponivel = estaDisponivel;
        this.estaEmBoasCondicoes = estaEmBoasCondicoes;
        this.estoqueAtual = estoqueAtual;
    }

    public boolean isPodeConsumir() { return podeConsumir; }
    public boolean isPodeConsumirComSeguranca() { return podeConsumirComSeguranca; }
    public boolean isEstaDisponivel() { return estaDisponivel; }
    public boolean isEstaEmBoasCondicoes() { return estaEmBoasCondicoes; }
    public Double getEstoqueAtual() { return estoqueAtual; }
}
