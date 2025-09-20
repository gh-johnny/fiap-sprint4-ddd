package com.dasa.keepinventory.api.controller;

import com.dasa.keepinventory.dao.AlmoxarifadoDao;
import com.dasa.keepinventory.model.Almoxarifado;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almoxarifados")
public class AlmoxarifadoController {

    private final AlmoxarifadoDao dao;

    public AlmoxarifadoController(AlmoxarifadoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Almoxarifado> getAll() {
        return dao.findAll();
    }

    @PostMapping
    public Almoxarifado create(@RequestBody Almoxarifado a) {
        return dao.save(a);
    }

    @GetMapping("/{id}")
    public Almoxarifado getOne(@PathVariable Long id) {
        return dao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }
}
