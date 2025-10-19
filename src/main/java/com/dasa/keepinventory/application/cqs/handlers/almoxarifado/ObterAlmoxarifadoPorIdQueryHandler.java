package com.dasa.keepinventory.application.cqs.handlers.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.almoxarifado.ObterAlmoxarifadoPorIdQuery;
import com.dasa.keepinventory.domain.entities.Almoxarifado;
import com.dasa.keepinventory.domain.repositories.AlmoxarifadoRepository;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ObterAlmoxarifadoPorIdQueryHandler 
        implements QueryHandler<ObterAlmoxarifadoPorIdQuery, Almoxarifado> {
    
    private final AlmoxarifadoRepository repository;

    public ObterAlmoxarifadoPorIdQueryHandler(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Almoxarifado> handle(ObterAlmoxarifadoPorIdQuery query) {
        try {
            Almoxarifado almoxarifado = repository.findById(query.getId())
                .orElseThrow(() -> new EntityNotFoundException("Almoxarifado", query.getId()));
            
            return Result.success(almoxarifado);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
