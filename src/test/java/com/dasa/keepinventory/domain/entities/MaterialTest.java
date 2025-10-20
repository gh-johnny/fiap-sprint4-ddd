package com.dasa.keepinventory.domain.entities;

import com.dasa.keepinventory.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MaterialTest {

    private Material material;

    @BeforeEach
    void setUp() {
        material = Material.builder()
            .nome("Luvas de Procedimento")
            .categoria("EPI")
            .unidadeMedida("UN")
            .quantidadeTotal(100.0)
            .build();
    }

    @Test
    void deveCriarMaterialComBuilder() {
        assertThat(material.getInformacao().getNome()).isEqualTo("Luvas de Procedimento");
        assertThat(material.getQuantidadeTotal().getValor()).isEqualTo(100.0);
    }

    @Test
    void deveAdicionarEstoque() {
        material.adicionarEstoque(Quantidade.of(50.0));
        
        assertThat(material.getQuantidadeTotal().getValor()).isEqualTo(150.0);
    }

    @Test
    void deveRemoverEstoque() {
        material.removerEstoque(Quantidade.of(30.0));
        
        assertThat(material.getQuantidadeTotal().getValor()).isEqualTo(70.0);
    }

    @Test
    void deveLancarExcecaoAoRemoverEstoqueInsuficiente() {
        assertThatThrownBy(() -> material.removerEstoque(Quantidade.of(150.0)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("insuficiente");
    }

    @Test
    void devePossuirEstoqueSuficiente() {
        assertThat(material.possuiEstoqueSuficiente(Quantidade.of(50.0))).isTrue();
        assertThat(material.possuiEstoqueSuficiente(Quantidade.of(100.0))).isTrue();
        assertThat(material.possuiEstoqueSuficiente(Quantidade.of(150.0))).isFalse();
    }

    @Test
    void devePrecisarReposicao() {
        Quantidade estoqueMinimo = Quantidade.of(120.0);
        
        assertThat(material.precisaReposicao(estoqueMinimo)).isTrue();
    }

    @Test
    void deveVerificarSeEstaCritico() {
        Quantidade nivelCritico = Quantidade.of(10.0);
        
        // Material com 100 unidades não está crítico
        assertThat(material.requerAtencaoUrgente.satisfeitoPor(nivelCritico)).isFalse();
        
        // Remove estoque até ficar crítico
        material.removerEstoque(Quantidade.of(95.0));
        assertThat(material.requerAtencaoUrgente.satisfeitoPor(nivelCritico)).isTrue();
    }

    @Test
    void deveReconstituirMaterialComId() {
        InformacaoMaterial info = InformacaoMaterial.of("Máscaras", "EPI", "UN");
        Quantidade qtd = Quantidade.of(50.0);
        
        Material materialReconstituido = Material.reconstituir(1L, info, qtd);
        
        assertThat(materialReconstituido.getId()).isEqualTo(1L);
        assertThat(materialReconstituido.getInformacao()).isEqualTo(info);
    }
}
