package com.dasa.keepinventory.api.controller;

import com.dasa.keepinventory.dao.MaterialDao;
import com.dasa.keepinventory.dao.RegistroConsumoDao;
import org.springframework.web.bind.annotation.*;
import com.dasa.keepinventory.dao.ResponsavelDao;
import com.dasa.keepinventory.dao.UnidadeDao;
import com.dasa.keepinventory.model.Material;
import com.dasa.keepinventory.model.RegistroConsumo;
import com.dasa.keepinventory.model.Responsavel;
import com.dasa.keepinventory.model.Unidade;
import java.util.List;
import com.dasa.keepinventory.api.dto.*;

@RestController
@RequestMapping("/registros")
public class RegistroConsumoController {

  private final RegistroConsumoDao registroDao;
  private final UnidadeDao unidadeDao;
  private final MaterialDao materialDao;
  private final ResponsavelDao responsavelDao;

  public RegistroConsumoController(
      RegistroConsumoDao registroDao,
      MaterialDao materialDao,
      UnidadeDao unidadeDao,
      ResponsavelDao responsavelDao) {
    this.registroDao = registroDao;
    this.materialDao = materialDao;
    this.unidadeDao = unidadeDao;
    this.responsavelDao = responsavelDao;
  }

  @GetMapping
  public List<RegistroConsumo> getAll() {
    return registroDao.findAll();
  }

  @PostMapping
  public RegistroConsumo create(@RequestBody RegistroConsumoDto dto) {
    Material mat = materialDao.findById(dto.idMaterial);
    Unidade uni = unidadeDao.findById(dto.idUnidade);
    Responsavel resp = responsavelDao.findById(dto.idResponsavel);

    RegistroConsumo reg = new RegistroConsumo(
        dto.quantidade,
        dto.dtConsumo,
        dto.observacao,
        mat,
        uni,
        resp);
    return registroDao.save(reg);
  }

  @GetMapping("/{id}")
  public RegistroConsumo getOne(@PathVariable Long id) {
    return registroDao.findById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    registroDao.delete(id);
  }
}
