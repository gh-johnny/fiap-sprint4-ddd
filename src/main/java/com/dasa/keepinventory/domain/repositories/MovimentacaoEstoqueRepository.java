package com.dasa.keepinventory.domain.repositories;

import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimentacaoEstoqueRepository {
    MovimentacaoEstoque save(MovimentacaoEstoque movimentacao);
    Optional<MovimentacaoEstoque> findById(Long id);
    List<MovimentacaoEstoque> findAll();
    List<MovimentacaoEstoque> findByMaterialId(Long materialId);
    List<MovimentacaoEstoque> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    void delete(Long id);
}
