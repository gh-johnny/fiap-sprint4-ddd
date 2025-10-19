package com.dasa.keepinventory.application.cqs.handlers.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.CommandHandler;
import com.dasa.keepinventory.application.cqs.commands.almoxarifado.CriarAlmoxarifadoCommand;
import com.dasa.keepinventory.domain.entities.Almoxarifado;
import com.dasa.keepinventory.domain.repositories.AlmoxarifadoRepository;
import com.dasa.keepinventory.shared.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CriarAlmoxarifadoCommandHandler 
        implements CommandHandler<CriarAlmoxarifadoCommand, Almoxarifado> {
    
    private final AlmoxarifadoRepository repository;

    public CriarAlmoxarifadoCommandHandler(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Result<Almoxarifado> handle(CriarAlmoxarifadoCommand command) {
        try {
            Almoxarifado almoxarifado = Almoxarifado.builder()
                .localizacao(command.getLocalizacao())
                .build();
            
            Almoxarifado saved = repository.save(almoxarifado);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
