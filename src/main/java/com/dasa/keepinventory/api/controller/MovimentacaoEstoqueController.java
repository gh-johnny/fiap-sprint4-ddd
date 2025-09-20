package com.dasa.keepinventory.api.controller;

import java.util.List;
import com.dasa.keepinventory.api.dto.MovimentacaoEstoqueDto;
import com.dasa.keepinventory.dao.MovimentacaoEstoqueDao;
import com.dasa.keepinventory.dao.MaterialDao;
import com.dasa.keepinventory.dao.AlmoxarifadoDao;
import com.dasa.keepinventory.dao.ResponsavelDao;
import com.dasa.keepinventory.model.MovimentacaoEstoque;
import com.dasa.keepinventory.model.Material;
import com.dasa.keepinventory.model.Almoxarifado;
import com.dasa.keepinventory.model.Responsavel;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueDao dao;
    private final MaterialDao materialDao;
    private final AlmoxarifadoDao almoxDao;
    private final ResponsavelDao respDao;

    public MovimentacaoEstoqueController(
        MovimentacaoEstoqueDao dao,
        MaterialDao materialDao,
        AlmoxarifadoDao almoxDao,
        ResponsavelDao respDao
    ) {
        this.dao = dao;
        this.materialDao = materialDao;
        this.almoxDao = almoxDao;
        this.respDao = respDao;
    }

    @GetMapping
    public List<MovimentacaoEstoque> getAll() {
        return dao.findAll();
    }

    @PostMapping
    public MovimentacaoEstoque create(@RequestBody MovimentacaoEstoqueDto dto) {
        Material mat = materialDao.findById(dto.idMaterial);
        Almoxarifado almox = almoxDao.findById(dto.idAlmoxarifado);
        Responsavel resp = respDao.findById(dto.idResponsavel);

        MovimentacaoEstoque mov = new MovimentacaoEstoque(
            dto.tipoMovimentacao,
            dto.quantidade,
            dto.dtMovimentacao,
            mat,
            almox,
            resp
        );

        return dao.save(mov);
    }

    @GetMapping("/{id}")
    public MovimentacaoEstoque getOne(@PathVariable Long id) {
        return dao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }
}
