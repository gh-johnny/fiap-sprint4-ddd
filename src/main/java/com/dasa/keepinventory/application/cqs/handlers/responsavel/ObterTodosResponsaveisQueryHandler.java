package com.dasa.keepinventory.application.cqs.handlers.responsavel;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.responsavel.ObterTodosResponsaveisQuery;
import com.dasa.keepinventory.domain.entities.Responsavel;
import com.dasa.keepinventory.domain.repositories.ResponsavelRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ObterTodosResponsaveisQueryHandler 
        implements QueryHandler<ObterTodosResponsaveisQuery, List<Responsavel>> {
    
    private final ResponsavelRepository repository;

    public ObterTodosResponsaveisQueryHandler(ResponsavelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Responsavel>> handle(ObterTodosResponsaveisQuery query) {
        try {
            List<Responsavel> responsaveis = repository.findAll();
            return Result.success(responsaveis);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
