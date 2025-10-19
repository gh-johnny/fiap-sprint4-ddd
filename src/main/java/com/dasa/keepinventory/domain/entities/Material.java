package com.dasa.keepinventory.domain.entities;

import com.dasa.keepinventory.domain.builders.base.Buildable;
import com.dasa.keepinventory.domain.builders.MaterialBuilder;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import com.dasa.keepinventory.domain.specifications.material.*;
import com.dasa.keepinventory.domain.valueobjects.*;

public class Material implements Buildable<Material> {
    private Long id;
    private InformacaoMaterial informacao;
    private Quantidade quantidadeTotal;

    private Material(Long id, InformacaoMaterial informacao, Quantidade quantidadeTotal) {
        this.id = id;
        this.informacao = informacao;
        this.quantidadeTotal = quantidadeTotal;
    }

    public static Material criar(InformacaoMaterial informacao, Quantidade quantidadeInicial) {
        return new Material(null, informacao, quantidadeInicial);
    }

    public static Material reconstituir(Long id, InformacaoMaterial informacao, Quantidade quantidade) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo ao reconstituir");
        }
        return new Material(id, informacao, quantidade);
    }

    public static MaterialBuilder builder() {
        return new MaterialBuilder();
    }

    public final RegraPodeSerConsumidoComSeguranca podeSerConsumidoComSeguranca = 
        new RegraPodeSerConsumidoComSeguranca();

    public final RegraRequerAtencaoUrgente requerAtencaoUrgente = 
        new RegraRequerAtencaoUrgente();

    public final RegraDisponivelParaRetirada disponivelParaRetirada = 
        new RegraDisponivelParaRetirada();

    public final RegraBoasCondicoes boasCondicoes = 
        new RegraBoasCondicoes();

    public class RegraPodeSerConsumidoComSeguranca {
        public boolean satisfeitoPor(Quantidade quantidadeDesejada, Quantidade nivelCritico) {
            Specification<Material> spec = new PossuiEstoqueSuficienteSpec(quantidadeDesejada)
                .e(new EstaCriticamenteBaixoSpec(nivelCritico).nao());
            return spec.satisfeitoPor(Material.this);
        }
    }

    public class RegraRequerAtencaoUrgente {
        public boolean satisfeitoPor(Quantidade nivelCritico) {
            Specification<Material> spec = new EstaCriticamenteBaixoSpec(nivelCritico)
                .ou(new EstaSemEstoqueSpec());
            return spec.satisfeitoPor(Material.this);
        }
    }

    public class RegraDisponivelParaRetirada {
        public boolean satisfeitoPor(Quantidade quantidadeSolicitada) {
            Specification<Material> spec = new EstaSemEstoqueSpec().nao()
                .e(new PossuiEstoqueSuficienteSpec(quantidadeSolicitada));
            return spec.satisfeitoPor(Material.this);
        }
    }

    public class RegraBoasCondicoes {
        public boolean satisfeitoPor(Quantidade estoqueMinimo) {
            Specification<Material> spec = new PossuiEstoqueSuficienteSpec(estoqueMinimo)
                .e(new EstaSemEstoqueSpec().nao());
            return spec.satisfeitoPor(Material.this);
        }
        
        public void validar(Quantidade estoqueMinimo) {
            if (!satisfeitoPor(estoqueMinimo)) {
                throw new IllegalStateException("Material não está em condições adequadas para operação");
            }
        }
    }

    public void adicionarEstoque(Quantidade quantidade) {
        this.quantidadeTotal = this.quantidadeTotal.adicionar(quantidade);
    }

    public void removerEstoque(Quantidade quantidade) {
        if (this.quantidadeTotal.menorQue(quantidade)) {
            throw new IllegalStateException("Estoque insuficiente para remover a quantidade solicitada");
        }
        this.quantidadeTotal = this.quantidadeTotal.subtrair(quantidade);
    }

    public boolean precisaReposicao(Quantidade estoqueMinimo) {
        return this.quantidadeTotal.menorQue(estoqueMinimo);
    }

    public boolean possuiEstoqueSuficiente(Quantidade quantidadeNecessaria) {
        return this.quantidadeTotal.maiorOuIgualA(quantidadeNecessaria);
    }

    // Getters
    public Long getId() { return id; }
    public InformacaoMaterial getInformacao() { return informacao; }
    public Quantidade getQuantidadeTotal() { return quantidadeTotal; }
    public void setId(Long id) { this.id = id; }
}
