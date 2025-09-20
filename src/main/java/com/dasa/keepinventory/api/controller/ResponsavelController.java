package com.dasa.keepinventory.api.controller;

import com.dasa.keepinventory.dao.ResponsavelDao;
import com.dasa.keepinventory.model.Responsavel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    private final ResponsavelDao dao;

    public ResponsavelController(ResponsavelDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Responsavel> getAll() {
        return dao.findAll();
    }

    @PostMapping
    public Responsavel create(@RequestBody Responsavel r) {
        return dao.save(r);
    }

    @GetMapping("/{id}")
    public Responsavel getOne(@PathVariable Long id) {
        return dao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }
}
