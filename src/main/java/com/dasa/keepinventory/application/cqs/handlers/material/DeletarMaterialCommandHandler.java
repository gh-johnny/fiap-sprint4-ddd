package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.material.DeletarMaterialCommand;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeletarMaterialCommandHandler implements CommandHandler<DeletarMaterialCommand, Void> {
    
    private final MaterialRepository repository;

    public DeletarMaterialCommandHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Void> handle(DeletarMaterialCommand command) {
        try {
            if (!repository.existsById(command.getId())) {
                throw new EntityNotFoundException("Material", command.getId());
            }
            
            repository.delete(command.getId());
            return Result.success(null);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
