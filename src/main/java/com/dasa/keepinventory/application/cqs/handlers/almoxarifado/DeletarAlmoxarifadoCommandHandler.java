package com.dasa.keepinventory.application.cqs.handlers.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.almoxarifado.DeletarAlmoxarifadoCommand;
import com.dasa.keepinventory.domain.repositories.AlmoxarifadoRepository;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeletarAlmoxarifadoCommandHandler 
        implements CommandHandler<DeletarAlmoxarifadoCommand, Void> {
    
    private final AlmoxarifadoRepository repository;

    public DeletarAlmoxarifadoCommandHandler(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Void> handle(DeletarAlmoxarifadoCommand command) {
        try {
            if (!repository.existsById(command.getId())) {
                throw new EntityNotFoundException("Almoxarifado", command.getId());
            }
            
            repository.delete(command.getId());
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
