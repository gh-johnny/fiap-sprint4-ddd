package com.dasa.keepinventory.api.controller;

import com.dasa.keepinventory.dao.UnidadeDao;
import com.dasa.keepinventory.model.Unidade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeDao dao;

    public UnidadeController(UnidadeDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Unidade> getAll() {
        return dao.findAll();
    }

    @PostMapping
    public Unidade create(@RequestBody Unidade u) {
        return dao.save(u);
    }

    @GetMapping("/{id}")
    public Unidade getOne(@PathVariable Long id) {
        return dao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }
}
