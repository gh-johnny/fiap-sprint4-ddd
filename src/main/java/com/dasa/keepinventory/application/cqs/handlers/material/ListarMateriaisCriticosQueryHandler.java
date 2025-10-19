package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.material.ListarMateriaisCriticosQuery;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class ListarMateriaisCriticosQueryHandler 
        implements QueryHandler<ListarMateriaisCriticosQuery, List<Material>> {
    
    private final MaterialRepository repository;

    public ListarMateriaisCriticosQueryHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Material>> handle(ListarMateriaisCriticosQuery query) {
        try {
            List<Material> todosMateriais = repository.findAll();
            Quantidade nivelCritico = Quantidade.of(query.getNivelCritico());
            
            List<Material> materiaisCriticos = todosMateriais.stream()
                .filter(m -> m.requerAtencaoUrgente.satisfeitoPor(nivelCritico))
                .collect(Collectors.toList());
            
            return Result.success(materiaisCriticos);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
