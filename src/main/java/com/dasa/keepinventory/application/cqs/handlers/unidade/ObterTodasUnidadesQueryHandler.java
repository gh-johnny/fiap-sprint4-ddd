package com.dasa.keepinventory.application.cqs.handlers.unidade;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.unidade.ObterTodasUnidadesQuery;
import com.dasa.keepinventory.domain.entities.Unidade;
import com.dasa.keepinventory.domain.repositories.UnidadeRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ObterTodasUnidadesQueryHandler 
        implements QueryHandler<ObterTodasUnidadesQuery, List<Unidade>> {
    
    private final UnidadeRepository repository;

    public ObterTodasUnidadesQueryHandler(UnidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Unidade>> handle(ObterTodasUnidadesQuery query) {
        try {
            List<Unidade> unidades = repository.findAll();
            return Result.success(unidades);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
