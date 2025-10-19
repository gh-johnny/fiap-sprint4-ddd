package com.dasa.keepinventory.application.cqs.commands.responsavel;

import com.dasa.keepinventory.application.cqs.base.Command;

public class CriarResponsavelCommand implements Command {
    private final String nome;
    private final String cargo;

    public CriarResponsavelCommand(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
}
