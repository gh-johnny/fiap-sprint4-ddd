package com.dasa.keepinventory.application.cqs.handlers.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.almoxarifado.ObterTodosAlmoxarifadosQuery;
import com.dasa.keepinventory.domain.entities.Almoxarifado;
import com.dasa.keepinventory.domain.repositories.AlmoxarifadoRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ObterTodosAlmoxarifadosQueryHandler 
        implements QueryHandler<ObterTodosAlmoxarifadosQuery, List<Almoxarifado>> {
    
    private final AlmoxarifadoRepository repository;

    public ObterTodosAlmoxarifadosQueryHandler(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<List<Almoxarifado>> handle(ObterTodosAlmoxarifadosQuery query) {
        try {
            List<Almoxarifado> almoxarifados = repository.findAll();
            return Result.success(almoxarifados);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
