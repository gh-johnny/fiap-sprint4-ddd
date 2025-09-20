package com.dasa.keepinventory.services;

import com.dasa.keepinventory.model.MovimentacaoEstoque;
import com.dasa.keepinventory.model.Material;
import com.dasa.keepinventory.model.RegistroConsumo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    // 1. Calcular estoque atual de um material
    public double calcularEstoqueAtual(Material material, List<MovimentacaoEstoque> movimentacoes) {
        double estoque = material.getQuantidadeTotal();
        for (MovimentacaoEstoque m : movimentacoes) {
            if (m.getMaterial().equals(material)) {
                if ("Entrada".equalsIgnoreCase(m.getTipoMovimentacao())) {
                    estoque += m.getQuantidade();
                } else if ("Saida".equalsIgnoreCase(m.getTipoMovimentacao())) {
                    estoque -= m.getQuantidade();
                }
            }
        }
        return estoque;
    }

    // 2. Registrar consumo de material
    public void registrarConsumo(RegistroConsumo consumo, Material material) {
        if (consumo.getQuantidade() > material.getQuantidadeTotal()) {
            throw new IllegalArgumentException("Consumo maior que o estoque disponível!");
        }
        material.setQuantidadeTotal(material.getQuantidadeTotal() - consumo.getQuantidade());
        // Aqui você poderia salvar o consumo em um DAO
    }

    // 3. Verificar se um material precisa de reposição (estoque mínimo = 10)
    public boolean precisaReposicao(Material material) {
        return material.getQuantidadeTotal() < 10;
    }

    // 4. Buscar movimentações de um material em um intervalo de datas
    public List<MovimentacaoEstoque> buscarMovimentacoesPorPeriodo(Material material,
                                                                   List<MovimentacaoEstoque> movimentacoes,
                                                                   LocalDateTime inicio,
                                                                   LocalDateTime fim) {
        return movimentacoes.stream()
                .filter(m -> m.getMaterial().equals(material))
                .filter(m -> !m.getDtMovimentacao().isBefore(inicio) && !m.getDtMovimentacao().isAfter(fim))
                .toList();
    }
}
