package com.dasa.keepinventory.application.cqs.handlers.responsavel;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.responsavel.CriarResponsavelCommand;
import com.dasa.keepinventory.domain.entities.Responsavel;
import com.dasa.keepinventory.domain.repositories.ResponsavelRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriarResponsavelCommandHandler 
        implements CommandHandler<CriarResponsavelCommand, Responsavel> {
    
    private final ResponsavelRepository repository;

    public CriarResponsavelCommandHandler(ResponsavelRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Responsavel> handle(CriarResponsavelCommand command) {
        try {
            Responsavel responsavel = Responsavel.builder()
                .nome(command.getNome())
                .cargo(command.getCargo())
                .build();
            
            Responsavel saved = repository.save(responsavel);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
