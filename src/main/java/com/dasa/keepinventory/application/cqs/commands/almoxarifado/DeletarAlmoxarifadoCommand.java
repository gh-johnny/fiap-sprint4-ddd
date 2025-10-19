package com.dasa.keepinventory.application.cqs.commands.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.Command;

public class DeletarAlmoxarifadoCommand implements Command {
    private final Long id;

    public DeletarAlmoxarifadoCommand(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
}
