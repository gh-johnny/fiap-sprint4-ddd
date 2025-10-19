package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.material.CriarMaterialCommand;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriarMaterialCommandHandler implements CommandHandler<CriarMaterialCommand, Material> {
    
    private final MaterialRepository repository;

    public CriarMaterialCommandHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Material> handle(CriarMaterialCommand command) {
        try {
            Material material = Material.builder()
                .nome(command.getNome())
                .categoria(command.getCategoria())
                .unidadeMedida(command.getUnidadeMedida())
                .quantidadeTotal(command.getQuantidadeInicial())
                .build();
            
            Material saved = repository.save(material);
            
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
