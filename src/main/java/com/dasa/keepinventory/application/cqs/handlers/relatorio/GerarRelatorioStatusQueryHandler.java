package com.dasa.keepinventory.application.cqs.handlers.relatorio;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.relatorio.*;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional(readOnly = true)
public class GerarRelatorioStatusQueryHandler 
        implements QueryHandler<GerarRelatorioStatusQuery, RelatorioStatusResponse> {
    
    private final MaterialRepository repository;
    private static final Quantidade NIVEL_CRITICO = Quantidade.of(10.0);
    private static final Quantidade ESTOQUE_MINIMO = Quantidade.of(20.0);

    public GerarRelatorioStatusQueryHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<RelatorioStatusResponse> handle(GerarRelatorioStatusQuery query) {
        try {
            List<Material> todosMateriais = repository.findAll();
            
            // Análise em lote usando propriedades de regras
            long materiaisCriticos = todosMateriais.stream()
                .filter(m -> m.requerAtencaoUrgente.satisfeitoPor(NIVEL_CRITICO))
                .count();
            
            long materiaisBoasCondicoes = todosMateriais.stream()
                .filter(m -> m.boasCondicoes.satisfeitoPor(ESTOQUE_MINIMO))
                .count();
            
            long materiaisDisponiveis = todosMateriais.stream()
                .filter(m -> m.disponivelParaRetirada.satisfeitoPor(Quantidade.of(1.0)))
                .count();
            
            // Distribuição por categoria
            Map<String, Long> porCategoria = new HashMap<>();
            todosMateriais.forEach(material -> {
                String categoria = material.getInformacao().getCategoria();
                if (categoria != null) {
                    porCategoria.merge(categoria, 1L, Long::sum);
                }
            });
            
            RelatorioStatusResponse response = new RelatorioStatusResponse(
                todosMateriais.size(),
                materiaisCriticos,
                materiaisBoasCondicoes,
                materiaisDisponiveis,
                porCategoria
            );
            
            return Result.success(response);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
