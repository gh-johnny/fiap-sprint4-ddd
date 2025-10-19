
package com.dasa.keepinventory.domain.aggregates;

import com.dasa.keepinventory.domain.entities.*;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import java.time.LocalDateTime;

public class RegistroConsumo {
    private Long id;
    private Quantidade quantidade;
    private LocalDateTime dataConsumo;
    private String observacao;
    private Material material;
    private Unidade unidade;
    private Responsavel responsavel;

    private RegistroConsumo(Long id, Quantidade quantidade, LocalDateTime dataConsumo,
                           String observacao, Material material, Unidade unidade,
                           Responsavel responsavel) {
        this.id = id;
        this.quantidade = quantidade;
        this.dataConsumo = dataConsumo;
        this.observacao = observacao;
        this.material = material;
        this.unidade = unidade;
        this.responsavel = responsavel;
    }

    public static RegistroConsumo registrar(
            Material material, Unidade unidade, Responsavel responsavel,
            Quantidade quantidade, String observacao) {
        
        validarEntidades(material, unidade, responsavel);
        
        if (!material.possuiEstoqueSuficiente(quantidade)) {
            throw new IllegalStateException("Estoque insuficiente para registrar o consumo");
        }
        
        material.removerEstoque(quantidade);
        
        return new RegistroConsumo(
            null, quantidade, LocalDateTime.now(),
            observacao, material, unidade, responsavel
        );
    }

    public static RegistroConsumo reconstituir(
            Long id, Quantidade quantidade, LocalDateTime dataConsumo,
            String observacao, Material material, Unidade unidade,
            Responsavel responsavel) {
        
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo ao reconstituir");
        }
        
        return new RegistroConsumo(
            id, quantidade, dataConsumo, observacao,
            material, unidade, responsavel
        );
    }

    private static void validarEntidades(Material material, Unidade unidade, Responsavel responsavel) {
        if (material == null || material.getId() == null) {
            throw new IllegalArgumentException("Material inválido");
        }
        if (unidade == null || unidade.getId() == null) {
            throw new IllegalArgumentException("Unidade inválida");
        }
        if (responsavel == null || responsavel.getId() == null) {
            throw new IllegalArgumentException("Responsável inválido");
        }
    }

    // Getters
    public Long getId() { return id; }
    public Quantidade getQuantidade() { return quantidade; }
    public LocalDateTime getDataConsumo() { return dataConsumo; }
    public String getObservacao() { return observacao; }
    public Material getMaterial() { return material; }
    public Unidade getUnidade() { return unidade; }
    public Responsavel getResponsavel() { return responsavel; }
    public void setId(Long id) { this.id = id; }
}
