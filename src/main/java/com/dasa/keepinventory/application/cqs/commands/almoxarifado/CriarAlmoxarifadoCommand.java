package com.dasa.keepinventory.application.cqs.commands.almoxarifado;

import com.dasa.keepinventory.application.cqs.base.Command;

public class CriarAlmoxarifadoCommand implements Command {
    private final String localizacao;

    public CriarAlmoxarifadoCommand(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getLocalizacao() { return localizacao; }
}
