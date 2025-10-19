package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.base.QueryHandler;
import com.dasa.keepinventory.application.cqs.queries.material.*;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ValidarConsumoMaterialQueryHandler 
        implements QueryHandler<ValidarConsumoMaterialQuery, ValidacaoConsumoResponse> {
    
    private final MaterialRepository repository;

    public ValidarConsumoMaterialQueryHandler(MaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<ValidacaoConsumoResponse> handle(ValidarConsumoMaterialQuery query) {
        try {
            Material material = repository.findById(query.getIdMaterial())
                .orElseThrow(() -> new EntityNotFoundException("Material", query.getIdMaterial()));
            
            Quantidade qtdSolicitada = Quantidade.of(query.getQuantidade());
            Quantidade nivelCritico = Quantidade.of(query.getNivelCritico());
            Quantidade estoqueMinimo = Quantidade.of(query.getEstoqueMinimo());
            
            // Usa propriedades de regras para avaliar
            boolean podeConsumirComSeguranca = material.podeSerConsumidoComSeguranca
                .satisfeitoPor(qtdSolicitada, nivelCritico);
            
            boolean estaDisponivel = material.disponivelParaRetirada
                .satisfeitoPor(qtdSolicitada);
            
            boolean estaEmBoasCondicoes = material.boasCondicoes
                .satisfeitoPor(estoqueMinimo);
            
            ValidacaoConsumoResponse response = new ValidacaoConsumoResponse(
                podeConsumirComSeguranca && estaDisponivel && estaEmBoasCondicoes,
                podeConsumirComSeguranca,
                estaDisponivel,
                estaEmBoasCondicoes,
                material.getQuantidadeTotal().getValor()
            );
            
            return Result.success(response);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
