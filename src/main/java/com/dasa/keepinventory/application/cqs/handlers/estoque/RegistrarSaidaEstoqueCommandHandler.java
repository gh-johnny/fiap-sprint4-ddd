package com.dasa.keepinventory.application.cqs.handlers.estoque;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.estoque.RegistrarSaidaEstoqueCommand;
import com.dasa.keepinventory.domain.aggregates.MovimentacaoEstoque;
import com.dasa.keepinventory.domain.entities.*;
import com.dasa.keepinventory.domain.repositories.*;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegistrarSaidaEstoqueCommandHandler 
        implements CommandHandler<RegistrarSaidaEstoqueCommand, MovimentacaoEstoque> {
    
    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final MaterialRepository materialRepository;
    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final ResponsavelRepository responsavelRepository;

    public RegistrarSaidaEstoqueCommandHandler(
            MovimentacaoEstoqueRepository movimentacaoRepository,
            MaterialRepository materialRepository,
            AlmoxarifadoRepository almoxarifadoRepository,
            ResponsavelRepository responsavelRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.materialRepository = materialRepository;
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    @Transactional
    public Result<MovimentacaoEstoque> handle(RegistrarSaidaEstoqueCommand command) {
        try {
            Material material = materialRepository.findById(command.getIdMaterial())
                .orElseThrow(() -> new EntityNotFoundException("Material", command.getIdMaterial()));
            
            Almoxarifado almoxarifado = almoxarifadoRepository.findById(command.getIdAlmoxarifado())
                .orElseThrow(() -> new EntityNotFoundException("Almoxarifado", command.getIdAlmoxarifado()));
            
            Responsavel responsavel = responsavelRepository.findById(command.getIdResponsavel())
                .orElseThrow(() -> new EntityNotFoundException("Responsavel", command.getIdResponsavel()));
            
            Quantidade quantidade = Quantidade.of(command.getQuantidade());
            
            MovimentacaoEstoque movimentacao = MovimentacaoEstoque.registrarSaida(
                material, almoxarifado, responsavel, quantidade
            );
            
            materialRepository.save(material);
            MovimentacaoEstoque saved = movimentacaoRepository.save(movimentacao);
            
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
