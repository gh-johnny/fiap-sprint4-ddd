package com.dasa.keepinventory.application.cqs.handlers.consumo;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.consumo.RegistrarConsumoCommand;
import com.dasa.keepinventory.domain.aggregates.RegistroConsumo;
import com.dasa.keepinventory.domain.entities.*;
import com.dasa.keepinventory.domain.repositories.*;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegistrarConsumoCommandHandler 
        implements CommandHandler<RegistrarConsumoCommand, RegistroConsumo> {
    
    private final RegistroConsumoRepository consumoRepository;
    private final MaterialRepository materialRepository;
    private final UnidadeRepository unidadeRepository;
    private final ResponsavelRepository responsavelRepository;

    public RegistrarConsumoCommandHandler(
            RegistroConsumoRepository consumoRepository,
            MaterialRepository materialRepository,
            UnidadeRepository unidadeRepository,
            ResponsavelRepository responsavelRepository) {
        this.consumoRepository = consumoRepository;
        this.materialRepository = materialRepository;
        this.unidadeRepository = unidadeRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    @Transactional
    public Result<RegistroConsumo> handle(RegistrarConsumoCommand command) {
        try {
            Material material = materialRepository.findById(command.getIdMaterial())
                .orElseThrow(() -> new EntityNotFoundException("Material", command.getIdMaterial()));
            
            Unidade unidade = unidadeRepository.findById(command.getIdUnidade())
                .orElseThrow(() -> new EntityNotFoundException("Unidade", command.getIdUnidade()));
            
            Responsavel responsavel = responsavelRepository.findById(command.getIdResponsavel())
                .orElseThrow(() -> new EntityNotFoundException("Responsavel", command.getIdResponsavel()));
            
            Quantidade quantidade = Quantidade.of(command.getQuantidade());
            
            RegistroConsumo consumo = RegistroConsumo.registrar(
                material, unidade, responsavel, quantidade, command.getObservacao()
            );
            
            materialRepository.save(material);
            RegistroConsumo saved = consumoRepository.save(consumo);
            
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
