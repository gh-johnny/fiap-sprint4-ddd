package com.dasa.keepinventory.domain.aggregates;

import com.dasa.keepinventory.domain.entities.*;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MovimentacaoEstoqueTest {

    private Material material;
    private Almoxarifado almoxarifado;
    private Responsavel responsavel;

    @BeforeEach
    void setUp() {
        material = Material.builder()
            .id(1L)
            .nome("Luvas")
            .categoria("EPI")
            .unidadeMedida("UN")
            .quantidadeTotal(100.0)
            .build();

        almoxarifado = Almoxarifado.builder()
            .id(1L)
            .localizacao("Prédio A")
            .build();

        responsavel = Responsavel.builder()
            .id(1L)
            .nome("João Silva")
            .cargo("Enfermeiro")
            .build();
    }

    @Test
    void deveRegistrarEntradaDeEstoque() {
        Quantidade quantidade = Quantidade.of(50.0);
        
        MovimentacaoEstoque movimentacao = MovimentacaoEstoque.registrarEntrada(
            material, almoxarifado, responsavel, quantidade
        );
        
        assertThat(movimentacao.getTipo()).isEqualTo(MovimentacaoEstoque.TipoMovimentacao.ENTRADA);
        assertThat(movimentacao.getQuantidade()).isEqualTo(quantidade);
        assertThat(movimentacao.getMaterial()).isEqualTo(material);
        assertThat(material.getQuantidadeTotal().getValor()).isEqualTo(150.0);
    }

    @Test
    void deveRegistrarSaidaDeEstoque() {
        Quantidade quantidade = Quantidade.of(30.0);
        
        MovimentacaoEstoque movimentacao = MovimentacaoEstoque.registrarSaida(
            material, almoxarifado, responsavel, quantidade
        );
        
        assertThat(movimentacao.getTipo()).isEqualTo(MovimentacaoEstoque.TipoMovimentacao.SAIDA);
        assertThat(material.getQuantidadeTotal().getValor()).isEqualTo(70.0);
    }

    @Test
    void deveLancarExcecaoAoRegistrarSaidaSemEstoque() {
        Quantidade quantidade = Quantidade.of(150.0);
        
        assertThatThrownBy(() -> MovimentacaoEstoque.registrarSaida(
            material, almoxarifado, responsavel, quantidade
        ))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("insuficiente");
    }

    @Test
    void deveLancarExcecaoComMaterialInvalido() {
        Material materialSemId = Material.builder()
            .nome("Material")
            .categoria("Cat")
            .unidadeMedida("UN")
            .quantidadeTotal(10.0)
            .build();
        
        assertThatThrownBy(() -> MovimentacaoEstoque.registrarEntrada(
            materialSemId, almoxarifado, responsavel, Quantidade.of(10.0)
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Material inválido");
    }
}
