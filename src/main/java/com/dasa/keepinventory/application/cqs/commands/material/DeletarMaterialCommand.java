package com.dasa.keepinventory.application.cqs.commands.material;

import com.dasa.keepinventory.application.cqs.base.Command;

public class DeletarMaterialCommand implements Command {
    private final Long id;

    public DeletarMaterialCommand(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
}
