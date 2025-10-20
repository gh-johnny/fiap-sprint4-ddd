package com.dasa.keepinventory.domain.aggregates;

import com.dasa.keepinventory.domain.entities.*;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RegistroConsumoTest {

    private Material material;
    private Unidade unidade;
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

        unidade = Unidade.builder()
            .id(1L)
            .nome("UTI")
            .setor("Emergência")
            .build();

        responsavel = Responsavel.builder()
            .id(1L)
            .nome("Maria Santos")
            .cargo("Técnica de Enfermagem")
            .build();
    }

    @Test
    void deveRegistrarConsumo() {
        Quantidade quantidade = Quantidade.of(20.0);
        String observacao = "Procedimento cirúrgico";
        
        RegistroConsumo consumo = RegistroConsumo.registrar(
            material, unidade, responsavel, quantidade, observacao
        );
        
        assertThat(consumo.getQuantidade()).isEqualTo(quantidade);
        assertThat(consumo.getObservacao()).isEqualTo(observacao);
        assertThat(consumo.getMaterial()).isEqualTo(material);
        assertThat(material.getQuantidadeTotal().getValor()).isEqualTo(80.0);
    }

    @Test
    void deveLancarExcecaoAoRegistrarConsumoSemEstoque() {
        Quantidade quantidade = Quantidade.of(150.0);
        
        assertThatThrownBy(() -> RegistroConsumo.registrar(
            material, unidade, responsavel, quantidade, "Teste"
        ))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("insuficiente");
    }
}
