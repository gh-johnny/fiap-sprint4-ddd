package com.dasa.keepinventory.application.cqs.handlers.material;

import com.dasa.keepinventory.application.cqs.commands.material.CriarMaterialCommand;
import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.repositories.MaterialRepository;
import com.dasa.keepinventory.shared.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarMaterialCommandHandlerTest {

    @Mock
    private MaterialRepository repository;

    @InjectMocks
    private CriarMaterialCommandHandler handler;

    private CriarMaterialCommand command;

    @BeforeEach
    void setUp() {
        command = new CriarMaterialCommand(
            "Luvas de Procedimento",
            "EPI",
            "UN",
            100.0
        );
    }

    @Test
    void deveCriarMaterialComSucesso() {
        Material materialSalvo = Material.builder()
            .id(1L)
            .nome("Luvas de Procedimento")
            .categoria("EPI")
            .unidadeMedida("UN")
            .quantidadeTotal(100.0)
            .build();

        when(repository.save(any(Material.class))).thenReturn(materialSalvo);

        Result<Material> result = handler.handle(command);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getData()).isNotNull();
        assertThat(result.getData().getId()).isEqualTo(1L);
        
        verify(repository, times(1)).save(any(Material.class));
    }

    @Test
    void deveRetornarErroQuandoRepositorioFalhar() {
        when(repository.save(any(Material.class)))
            .thenThrow(new RuntimeException("Erro no banco de dados"));

        Result<Material> result = handler.handle(command);

        assertThat(result.isFailure()).isTrue();
        assertThat(result.getError()).contains("Erro no banco de dados");
    }
}
