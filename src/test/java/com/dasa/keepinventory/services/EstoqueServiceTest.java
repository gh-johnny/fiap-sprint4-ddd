package com.dasa.keepinventory.services;

import com.dasa.keepinventory.model.Material;
import com.dasa.keepinventory.model.MovimentacaoEstoque;
import com.dasa.keepinventory.model.RegistroConsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EstoqueServiceTest {

    private EstoqueService estoqueService;
    private Material material;

    @BeforeEach
    void setUp() {
        estoqueService = new EstoqueService();
        material = new Material();
        material.setId(1L);
        material.setNome("Álcool Gel");
        material.setQuantidadeTotal(50.0);
    }

    @Test
    void deveCalcularEstoqueAtualComEntradasESaidas() {
        MovimentacaoEstoque entrada = new MovimentacaoEstoque();
        entrada.setMaterial(material);
        entrada.setTipoMovimentacao("Entrada");
        entrada.setQuantidade(20.0);

        MovimentacaoEstoque saida = new MovimentacaoEstoque();
        saida.setMaterial(material);
        saida.setTipoMovimentacao("Saida");
        saida.setQuantidade(10.0);

        double estoque = estoqueService.calcularEstoqueAtual(material, List.of(entrada, saida));

        assertThat(estoque).isEqualTo(60.0); // 50 + 20 - 10
    }
    
    @Test
    void deveRegistrarConsumoComSucesso() {
        RegistroConsumo consumo = new RegistroConsumo();
        consumo.setMaterial(material);
        consumo.setQuantidade(15.0);
    
        estoqueService.registrarConsumo(consumo, material);
    
        assertThat(material.getQuantidadeTotal()).isEqualTo(35.0); // 50 - 15
    }
    
    @Test
    void deveLancarErroQuandoConsumoMaiorQueEstoque() {
        RegistroConsumo consumo = new RegistroConsumo();
        consumo.setMaterial(material);
        consumo.setQuantidade(100.0);
    
        assertThatThrownBy(() -> estoqueService.registrarConsumo(consumo, material))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Consumo maior que o estoque disponível!");
    }
    
    @Test
    void deveDetectarQueMaterialPrecisaReposicao() {
        material.setQuantidadeTotal(5.0);
    
        boolean precisa = estoqueService.precisaReposicao(material);
    
        assertThat(precisa).isTrue();
    }
    
    @Test
    void deveRetornarMovimentacoesNoPeriodo() {
        LocalDateTime agora = LocalDateTime.now();
    
        MovimentacaoEstoque mov1 = new MovimentacaoEstoque();
        mov1.setMaterial(material);
        mov1.setDtMovimentacao(agora.minusDays(2));
        mov1.setTipoMovimentacao("Entrada");
        mov1.setQuantidade(10.0);
    
        MovimentacaoEstoque mov2 = new MovimentacaoEstoque();
        mov2.setMaterial(material);
        mov2.setDtMovimentacao(agora.minusDays(1));
        mov2.setTipoMovimentacao("Saida");
        mov2.setQuantidade(5.0);
    
        MovimentacaoEstoque mov3 = new MovimentacaoEstoque();
        mov3.setMaterial(material);
        mov3.setDtMovimentacao(agora.minusDays(10)); // fora do período
        mov3.setTipoMovimentacao("Entrada");
        mov3.setQuantidade(50.0);
    
        List<MovimentacaoEstoque> result = estoqueService.buscarMovimentacoesPorPeriodo(
                material,
                List.of(mov1, mov2, mov3),
                agora.minusDays(3),
                agora
        );
    
        assertThat(result).containsExactlyInAnyOrder(mov1, mov2);
        assertThat(result).doesNotContain(mov3);
    }
}
