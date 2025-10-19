package com.dasa.keepinventory.domain.aggregates;

import com.dasa.keepinventory.domain.entities.*;
import com.dasa.keepinventory.domain.specifications.base.Specification;
import com.dasa.keepinventory.domain.specifications.movimentacao.*;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import java.time.LocalDateTime;

public class MovimentacaoEstoque {
    private Long id;
    private TipoMovimentacao tipo;
    private Quantidade quantidade;
    private LocalDateTime dataMovimentacao;
    private Material material;
    private Almoxarifado almoxarifado;
    private Responsavel responsavel;

    // Construtor privado
    private MovimentacaoEstoque(Long id, TipoMovimentacao tipo, Quantidade quantidade,
                               LocalDateTime dataMovimentacao, Material material,
                               Almoxarifado almoxarifado, Responsavel responsavel) {
        this.id = id;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
        this.material = material;
        this.almoxarifado = almoxarifado;
        this.responsavel = responsavel;
    }

    // Static factory methods
    public static MovimentacaoEstoque registrarEntrada(
            Material material, Almoxarifado almoxarifado, 
            Responsavel responsavel, Quantidade quantidade) {
        
        validarEntidades(material, almoxarifado, responsavel);
        material.adicionarEstoque(quantidade);
        
        return new MovimentacaoEstoque(
            null, TipoMovimentacao.ENTRADA, quantidade,
            LocalDateTime.now(), material, almoxarifado, responsavel
        );
    }

    public static MovimentacaoEstoque registrarSaida(
            Material material, Almoxarifado almoxarifado,
            Responsavel responsavel, Quantidade quantidade) {
        
        validarEntidades(material, almoxarifado, responsavel);
        
        if (!material.possuiEstoqueSuficiente(quantidade)) {
            throw new IllegalStateException("Estoque insuficiente para realizar a saída");
        }
        
        material.removerEstoque(quantidade);
        
        return new MovimentacaoEstoque(
            null, TipoMovimentacao.SAIDA, quantidade,
            LocalDateTime.now(), material, almoxarifado, responsavel
        );
    }

    public static MovimentacaoEstoque reconstituir(
            Long id, TipoMovimentacao tipo, Quantidade quantidade,
            LocalDateTime dataMovimentacao, Material material,
            Almoxarifado almoxarifado, Responsavel responsavel) {
        
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo ao reconstituir");
        }
        
        return new MovimentacaoEstoque(
            id, tipo, quantidade, dataMovimentacao,
            material, almoxarifado, responsavel
        );
    }

    public final RegraEntradaSignificativa entradaSignificativa = 
        new RegraEntradaSignificativa();

    public final RegraSaidaRecenteGrande saidaRecenteGrande = 
        new RegraSaidaRecenteGrande();

    public final RegraMovimentacaoSuspeita movimentacaoSuspeita = 
        new RegraMovimentacaoSuspeita();

    public class RegraEntradaSignificativa {
        public boolean satisfeitoPor(Quantidade limiar) {
            Specification<MovimentacaoEstoque> spec = new EhDoTipoSpec(TipoMovimentacao.ENTRADA)
                .e(new ExcedeQuantidadeSpec(limiar));
            return spec.satisfeitoPor(MovimentacaoEstoque.this);
        }
    }

    public class RegraSaidaRecenteGrande {
        public boolean satisfeitoPor(LocalDateTime dataInicio, Quantidade limiar) {
            LocalDateTime agora = LocalDateTime.now();
            Specification<MovimentacaoEstoque> spec = new EhDoTipoSpec(TipoMovimentacao.SAIDA)
                .e(new OcorreuNoPeriodoSpec(dataInicio, agora))
                .e(new ExcedeQuantidadeSpec(limiar));
            return spec.satisfeitoPor(MovimentacaoEstoque.this);
        }
    }

    public class RegraMovimentacaoSuspeita {
        public boolean satisfeitoPor(LocalDateTime dataInicio, Quantidade limiarSuspeito) {
            LocalDateTime agora = LocalDateTime.now();
            Specification<MovimentacaoEstoque> spec = new OcorreuNoPeriodoSpec(dataInicio, agora)
                .e(new ExcedeQuantidadeSpec(limiarSuspeito));
            return spec.satisfeitoPor(MovimentacaoEstoque.this);
        }
    }

    private static void validarEntidades(Material material, Almoxarifado almoxarifado, Responsavel responsavel) {
        if (material == null || material.getId() == null) {
            throw new IllegalArgumentException("Material inválido");
        }
        if (almoxarifado == null || almoxarifado.getId() == null) {
            throw new IllegalArgumentException("Almoxarifado inválido");
        }
        if (responsavel == null || responsavel.getId() == null) {
            throw new IllegalArgumentException("Responsável inválido");
        }
    }

    // Getters
    public Long getId() { return id; }
    public TipoMovimentacao getTipo() { return tipo; }
    public Quantidade getQuantidade() { return quantidade; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public Material getMaterial() { return material; }
    public Almoxarifado getAlmoxarifado() { return almoxarifado; }
    public Responsavel getResponsavel() { return responsavel; }
    public void setId(Long id) { this.id = id; }

    public enum TipoMovimentacao {
        ENTRADA, SAIDA;
        
        public static TipoMovimentacao fromString(String tipo) {
            if (tipo == null) {
                throw new IllegalArgumentException("Tipo de movimentação não pode ser nulo");
            }
            return TipoMovimentacao.valueOf(tipo.toUpperCase());
        }
    }
}
