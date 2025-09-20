package com.dasa.keepinventory.api.controller;

import com.dasa.keepinventory.dao.MaterialDao;
import com.dasa.keepinventory.model.Material;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialDao dao;

    public MaterialController(MaterialDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Material> getAll() {
        return dao.findAll();
    }

    @PostMapping
    public Material create(@RequestBody Material m) {
        return dao.save(m);
    }

    @GetMapping("/{id}")
    public Material getOne(@PathVariable Long id) {
        return dao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }
}
