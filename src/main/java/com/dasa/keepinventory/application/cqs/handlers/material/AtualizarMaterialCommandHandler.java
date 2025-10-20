package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.material.AtualizarMaterialCommand;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.domain.valueobjects.InformacaoMaterial;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AtualizarMaterialCommandHandler implements CommandHandler<AtualizarMaterialCommand, Material> {
    
    private final MaterialRepository repository;

    public AtualizarMaterialCommandHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Material> handle(AtualizarMaterialCommand command) {
        try {
            // Busca o material existente
            Material material = repository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException("Material", command.getId()));
            
            // Reconstrói o material com os novos dados
            InformacaoMaterial novaInfo = InformacaoMaterial.of(
                command.getNome(),
                command.getCategoria(),
                command.getUnidadeMedida()
            );
            
            Quantidade novaQuantidade = Quantidade.of(command.getQuantidadeTotal());
            
            Material materialAtualizado = Material.reconstituir(
                command.getId(),
                novaInfo,
                novaQuantidade
            );
            
            Material saved = repository.save(materialAtualizado);
            
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
