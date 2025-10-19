package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.material.ObterTodosMateriaisQuery;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ObterTodosMateriaisQueryHandler implements QueryHandler<ObterTodosMateriaisQuery, List<Material>> {
    
    private final MaterialRepository repository;

    public ObterTodosMateriaisQueryHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Material>> handle(ObterTodosMateriaisQuery query) {
        try {
            List<Material> materiais = repository.findAll();
            return Result.success(materiais);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
