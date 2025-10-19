package com.dasa.keepinventory.application.cqs.handlers.unidade;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.unidade.CriarUnidadeCommand;
import com.dasa.keepinventory.domain.entities.Unidade;
import com.dasa.keepinventory.domain.repositories.UnidadeRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriarUnidadeCommandHandler 
        implements CommandHandler<CriarUnidadeCommand, Unidade> {
    
    private final UnidadeRepository repository;

    public CriarUnidadeCommandHandler(UnidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Unidade> handle(CriarUnidadeCommand command) {
        try {
            Unidade unidade = Unidade.builder()
                .nome(command.getNome())
                .setor(command.getSetor())
                .build();
            
            Unidade saved = repository.save(unidade);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
