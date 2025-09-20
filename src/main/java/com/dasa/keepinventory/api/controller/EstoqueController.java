package com.dasa.keepinventory.api.controller;

import com.dasa.keepinventory.dao.*;
import com.dasa.keepinventory.model.*;
import com.dasa.keepinventory.services.EstoqueService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
  private final EstoqueService estoqueService;
  private final MovimentacaoEstoqueDao movimentacaoDao;
  private final MaterialDao materialDao;

  public EstoqueController(EstoqueService estoqueService,
      MovimentacaoEstoqueDao movimentacaoDao,
      MaterialDao materialDao) {
    this.estoqueService = estoqueService;
    this.movimentacaoDao = movimentacaoDao;
    this.materialDao = materialDao;
  }

  @GetMapping("/{idMaterial}/atual")
  public double getEstoqueAtual(@PathVariable Long idMaterial) {
    Material material = materialDao.findById(idMaterial);
    List<MovimentacaoEstoque> movimentacoes = movimentacaoDao.findAll();
    return estoqueService.calcularEstoqueAtual(material, movimentacoes);
  }

  @PostMapping("/{idMaterial}/consumo")
  public String registrarConsumo(@PathVariable Long idMaterial, @RequestBody RegistroConsumo consumo) {
    Material material = materialDao.findById(idMaterial);
    estoqueService.registrarConsumo(consumo, material);
    return "Consumo registrado com sucesso!";
  }

  @GetMapping("/{idMaterial}/reposicao")
  public boolean precisaReposicao(@PathVariable Long idMaterial) {
    Material material = materialDao.findById(idMaterial);
    return estoqueService.precisaReposicao(material);
  }

  @GetMapping("/{idMaterial}/movimentacoes")
  public List<MovimentacaoEstoque> buscarMovimentacoesPorPeriodo(
      @PathVariable Long idMaterial,
      @RequestParam String inicio,
      @RequestParam String fim) {

    Material material = materialDao.findById(idMaterial);
    List<MovimentacaoEstoque> movimentacoes = movimentacaoDao.findAll();

    LocalDateTime dtInicio = LocalDateTime.parse(inicio);
    LocalDateTime dtFim = LocalDateTime.parse(fim);

    return estoqueService.buscarMovimentacoesPorPeriodo(material, movimentacoes, dtInicio, dtFim);
  }
}
