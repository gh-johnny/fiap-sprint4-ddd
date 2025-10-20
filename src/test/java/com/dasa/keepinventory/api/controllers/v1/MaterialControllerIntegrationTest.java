package com.dasa.keepinventory.api.controllers.v1;

import com.dasa.keepinventory.api.dto.request.CreateMaterialRequest;
import com.dasa.keepinventory.api.dto.request.UpdateMaterialRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class MaterialControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarMaterialComSucesso() throws Exception {
        CreateMaterialRequest request = new CreateMaterialRequest();
        request.setNome("Luvas de Procedimento");
        request.setCategoria("EPI");
        request.setUnidadeMedida("UN");
        request.setQuantidadeInicial(100.0);

        mockMvc.perform(post("/api/v1/materiais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.nome").value("Luvas de Procedimento"))
            .andExpect(jsonPath("$.data.quantidadeTotal").value(100.0));
    }

    @Test
    void deveRetornarErroAoCriarMaterialSemNome() throws Exception {
        CreateMaterialRequest request = new CreateMaterialRequest();
        request.setCategoria("EPI");
        request.setUnidadeMedida("UN");
        request.setQuantidadeInicial(100.0);

        mockMvc.perform(post("/api/v1/materiais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void deveListarTodosMateriais() throws Exception {
        mockMvc.perform(get("/api/v1/materiais"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void deveRetornarNotFoundAoBuscarMaterialInexistente() throws Exception {
        mockMvc.perform(get("/api/v1/materiais/99999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.success").value(false));
    }
}
