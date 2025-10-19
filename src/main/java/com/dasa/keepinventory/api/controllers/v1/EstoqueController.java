package com.dasa.keepinventory.api.controllers.v1;

import com.dasa.keepinventory.api.dto.request.StockMovementRequest;
import com.dasa.keepinventory.api.dto.response.ApiResponse;
import com.dasa.keepinventory.api.mappers.ResultToApiResponseMapper;
import com.dasa.keepinventory.application.cqs.commands.estoque.*;
import com.dasa.keepinventory.application.cqs.handlers.estoque.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/estoque")
@Tag(name = "Estoque", description = "Operações de movimentação de estoque")
public class EstoqueController {

    // Command Handlers
    private final RegistrarEntradaEstoqueCommandHandler registrarEntradaHandler;
    private final RegistrarSaidaEstoqueCommandHandler registrarSaidaHandler;
   
    // Mapper
    private final ResultToApiResponseMapper resultMapper;

    public EstoqueController(
            RegistrarEntradaEstoqueCommandHandler registrarEntradaHandler,
            RegistrarSaidaEstoqueCommandHandler registrarSaidaHandler,
            ResultToApiResponseMapper resultMapper) {
        this.registrarEntradaHandler = registrarEntradaHandler;
        this.registrarSaidaHandler = registrarSaidaHandler;
        this.resultMapper = resultMapper;
    }

    @PostMapping("/entrada")
    @Operation(summary = "Registrar entrada de estoque", description = "Adiciona quantidade ao estoque de um material")
    public ResponseEntity<ApiResponse<String>> registerEntry(@Valid @RequestBody StockMovementRequest request) {
        // Cria command
        var command = new RegistrarEntradaEstoqueCommand(
            request.getIdMaterial(),
            request.getIdAlmoxarifado(),
            request.getIdResponsavel(),
            request.getQuantidade()
        );

        // Executa command através do handler
        var result = registrarEntradaHandler.handle(command);
        var response = resultMapper.map(result.map(m -> "Entrada registrada com sucesso"));

        return response.isSuccess()
            ? ResponseEntity.status(HttpStatus.CREATED).body(response)
            : ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/saida")
    @Operation(summary = "Registrar saída de estoque", description = "Remove quantidade do estoque de um material")
    public ResponseEntity<ApiResponse<String>> registerExit(@Valid @RequestBody StockMovementRequest request) {
        // Cria command
        var command = new RegistrarSaidaEstoqueCommand(
            request.getIdMaterial(),
            request.getIdAlmoxarifado(),
            request.getIdResponsavel(),
            request.getQuantidade()
        );

        // Executa command através do handler
        var result = registrarSaidaHandler.handle(command);
        var response = resultMapper.map(result.map(m -> "Saída registrada com sucesso"));

        return response.isSuccess()
            ? ResponseEntity.status(HttpStatus.CREATED).body(response)
            : ResponseEntity.badRequest().body(response);
    }
}
