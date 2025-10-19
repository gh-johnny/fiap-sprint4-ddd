package com.dasa.keepinventory.application.cqs.commands.unidade;

import com.dasa.keepinventory.application.cqs.base.Command;

public class CriarUnidadeCommand implements Command {
    private final String nome;
    private final String setor;

    public CriarUnidadeCommand(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
    }

    public String getNome() { return nome; }
    public String getSetor() { return setor; }
}
