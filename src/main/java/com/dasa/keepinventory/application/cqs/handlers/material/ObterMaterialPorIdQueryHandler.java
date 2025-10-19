package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.material.ObterMaterialPorIdQuery;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ObterMaterialPorIdQueryHandler implements QueryHandler<ObterMaterialPorIdQuery, Material> {
    
    private final MaterialRepository repository;

    public ObterMaterialPorIdQueryHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Material> handle(ObterMaterialPorIdQuery query) {
        try {
            Material material = repository.findById(query.getId())
                .orElseThrow(() -> new EntityNotFoundException("Material", query.getId()));
            
            return Result.success(material);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
