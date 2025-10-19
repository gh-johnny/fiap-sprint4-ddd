package com.dasa.keepinventory.api.controllers.v1;

import com.dasa.keepinventory.api.dto.request.RegisterConsumptionRequest;
import com.dasa.keepinventory.api.dto.response.ApiResponse;
import com.dasa.keepinventory.api.mappers.ResultToApiResponseMapper;
import com.dasa.keepinventory.application.cqs.commands.consumo.*;
import com.dasa.keepinventory.application.cqs.handlers.consumo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consumo")
@Tag(name = "Consumo", description = "Operações de registro de consumo de materiais")
public class ConsumoController {

    // Command Handler
    private final RegistrarConsumoCommandHandler registrarConsumoHandler;
    
    // Mapper
    private final ResultToApiResponseMapper resultMapper;

    public ConsumoController(
            RegistrarConsumoCommandHandler registrarConsumoHandler,
            ResultToApiResponseMapper resultMapper) {
        this.registrarConsumoHandler = registrarConsumoHandler;
        this.resultMapper = resultMapper;
    }

    @PostMapping
    @Operation(summary = "Registrar consumo", description = "Registra o consumo de um material por uma unidade")
    public ResponseEntity<ApiResponse<String>> registerConsumption(@Valid @RequestBody RegisterConsumptionRequest request) {
        // Cria command
        var command = new RegistrarConsumoCommand(
            request.getIdMaterial(),
            request.getIdUnidade(),
            request.getIdResponsavel(),
            request.getQuantidade(),
            request.getObservacao()
        );

        // Executa command através do handler
        var result = registrarConsumoHandler.handle(command);
        var response = resultMapper.map(result.map(c -> "Consumo registrado com sucesso"));

        return response.isSuccess()
            ? ResponseEntity.status(HttpStatus.CREATED).body(response)
            : ResponseEntity.badRequest().body(response);
    }
}
