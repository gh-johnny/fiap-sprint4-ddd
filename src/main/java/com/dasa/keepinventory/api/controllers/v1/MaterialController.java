package com.dasa.keepinventory.api.controllers.v1;

import com.dasa.keepinventory.api.dto.response.ApiResponse;
import com.dasa.keepinventory.api.dto.response.MaterialResponse;
import com.dasa.keepinventory.api.mappers.MaterialResponseMapper;
import com.dasa.keepinventory.api.mappers.ResultToApiResponseMapper;
import com.dasa.keepinventory.application.cqs.commands.material.CriarMaterialCommand;
import com.dasa.keepinventory.application.cqs.commands.material.DeletarMaterialCommand;
import com.dasa.keepinventory.application.cqs.commands.material.AtualizarMaterialCommand;
import com.dasa.keepinventory.application.cqs.handlers.material.*;
import com.dasa.keepinventory.application.cqs.queries.material.ListarMateriaisCriticosQuery;
import com.dasa.keepinventory.application.cqs.queries.material.ObterMaterialPorIdQuery;
import com.dasa.keepinventory.application.cqs.queries.material.ObterTodosMateriaisQuery;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.dasa.keepinventory.api.dto.request.CreateMaterialRequest;
import com.dasa.keepinventory.api.dto.request.UpdateMaterialRequest;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/materiais")
@Tag(name = "Materiais", description = "Operações relacionadas a materiais")
public class MaterialController {

    // Command Handlers
    private final CriarMaterialCommandHandler criarMaterialHandler;
    private final DeletarMaterialCommandHandler deletarMaterialHandler;
    private final AtualizarMaterialCommandHandler atualizarMaterialCommandHandler;
    
    // Query Handlers
    private final ObterTodosMateriaisQueryHandler obterTodosHandler;
    private final ObterMaterialPorIdQueryHandler obterPorIdHandler;
    private final ListarMateriaisCriticosQueryHandler listarCriticosHandler;
    
    // Mappers
    private final MaterialResponseMapper responseMapper;
    private final ResultToApiResponseMapper resultMapper;

    public MaterialController(
            CriarMaterialCommandHandler criarMaterialHandler,
            DeletarMaterialCommandHandler deletarMaterialHandler,
            ObterTodosMateriaisQueryHandler obterTodosHandler,
            ObterMaterialPorIdQueryHandler obterPorIdHandler,
            ListarMateriaisCriticosQueryHandler listarCriticosHandler,
            AtualizarMaterialCommandHandler atualizarMaterialCommandHandler,
            MaterialResponseMapper responseMapper,
            ResultToApiResponseMapper resultMapper) {
        this.criarMaterialHandler = criarMaterialHandler;
        this.deletarMaterialHandler = deletarMaterialHandler;
        this.obterTodosHandler = obterTodosHandler;
        this.obterPorIdHandler = obterPorIdHandler;
        this.listarCriticosHandler = listarCriticosHandler;
        this.atualizarMaterialCommandHandler = atualizarMaterialCommandHandler;
        this.responseMapper = responseMapper;
        this.resultMapper = resultMapper;
    }

    @PostMapping
    @Operation(summary = "Criar novo material", description = "Cria um novo material no sistema")
    public ResponseEntity<ApiResponse<MaterialResponse>> create(@Valid @RequestBody CreateMaterialRequest request) {
        // Cria command
        var command = new CriarMaterialCommand(
            request.getNome(),
            request.getCategoria(),
            request.getUnidadeMedida(),
            request.getQuantidadeInicial()
        );

        // Executa command através do handler
        var result = criarMaterialHandler.handle(command);
        var response = resultMapper.map(result.map(responseMapper::toResponse));

        return response.isSuccess() 
            ? ResponseEntity.status(HttpStatus.CREATED).body(response)
            : ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar material", description = "Atualiza os dados de um material existente")
    public ResponseEntity<ApiResponse<MaterialResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMaterialRequest request) {
        
        var command = new AtualizarMaterialCommand(
            id,
            request.getNome(),
            request.getCategoria(),
            request.getUnidadeMedida(),
            request.getQuantidadeTotal()
        );

        var result = atualizarMaterialCommandHandler.handle(command);
        var response = resultMapper.map(result.map(responseMapper::toResponse));

        return response.isSuccess()
            ? ResponseEntity.ok(response)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os materiais", description = "Retorna lista de todos os materiais cadastrados")
    public ResponseEntity<ApiResponse<List<MaterialResponse>>> getAll() {
        // Cria query
        var query = new ObterTodosMateriaisQuery();
        
        // Executa query através do handler
        var result = obterTodosHandler.handle(query);
        var response = resultMapper.map(result.map(responseMapper::toResponseList));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar material por ID", description = "Retorna um material específico pelo ID")
    public ResponseEntity<ApiResponse<MaterialResponse>> getById(@PathVariable Long id) {
        // Cria query
        var query = new ObterMaterialPorIdQuery(id);
        
        // Executa query através do handler
        var result = obterPorIdHandler.handle(query);
        var response = resultMapper.map(result.map(responseMapper::toResponse));

        return response.isSuccess()
            ? ResponseEntity.ok(response)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/criticos")
    @Operation(summary = "Listar materiais críticos", description = "Retorna materiais que requerem atenção urgente")
    public ResponseEntity<ApiResponse<List<MaterialResponse>>> getCriticos(
            @RequestParam(defaultValue = "10.0") Double nivelCritico) {
        // Cria query
        var query = new ListarMateriaisCriticosQuery(nivelCritico);
        
        // Executa query através do handler
        var result = listarCriticosHandler.handle(query);
        var response = resultMapper.map(result.map(responseMapper::toResponseList));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar material", description = "Remove um material do sistema")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        // Cria command
        var command = new DeletarMaterialCommand(id);
        
        // Executa command através do handler
        var result = deletarMaterialHandler.handle(command);
        var response = resultMapper.map(result);

        return response.isSuccess()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
