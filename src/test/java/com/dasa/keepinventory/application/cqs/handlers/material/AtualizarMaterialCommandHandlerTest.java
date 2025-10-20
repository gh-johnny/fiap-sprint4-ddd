package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.commands.material.AtualizarMaterialCommand;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.shared.Result;
import com.dasa.keepinventory.shared.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarMaterialCommandHandlerTest {

    @Mock
    private MaterialRepository repository;

    @InjectMocks
    private AtualizarMaterialCommandHandler handler;

    private Material materialExistente;
    private AtualizarMaterialCommand command;

    @BeforeEach
    void setUp() {
        materialExistente = Material.builder()
            .id(1L)
            .nome("Luvas")
            .categoria("EPI")
            .unidadeMedida("UN")
            .quantidadeTotal(100.0)
            .build();

        command = new AtualizarMaterialCommand(
            1L,
            "Luvas Tamanho M",
            "EPI",
            "CAIXA",
            150.0
        );
    }

    @Test
    void deveAtualizarMaterialComSucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(materialExistente));
        when(repository.save(any(Material.class))).thenReturn(materialExistente);

        Result<Material> result = handler.handle(command);

        assertThat(result.isSuccess()).isTrue();
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Material.class));
    }

    @Test
    void deveRetornarErroQuandoMaterialNaoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Result<Material> result = handler.handle(command);

        assertThat(result.isFailure()).isTrue();
        assertThat(result.getError()).contains("não encontrado");
        verify(repository, never()).save(any(Material.class));
    }
}
