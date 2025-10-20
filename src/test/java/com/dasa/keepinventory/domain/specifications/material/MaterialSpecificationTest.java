package com.dasa.keepinventory.domain.specifications.material;

import com.dasa.keepinventory.domain.entities.Material;
import com.dasa.keepinventory.domain.valueobjects.Quantidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MaterialSpecificationTest {

    private Material material;

    @BeforeEach
    void setUp() {
        material = Material.builder()
            .nome("Luvas")
            .categoria("EPI")
            .unidadeMedida("UN")
            .quantidadeTotal(100.0)
            .build();
    }

    @Test
    void deveVerificarEstoqueSuficiente() {
        PossuiEstoqueSuficienteSpec spec = new PossuiEstoqueSuficienteSpec(Quantidade.of(50.0));
        
        assertThat(spec.satisfeitoPor(material)).isTrue();
    }

    @Test
    void deveVerificarEstoqueInsuficiente() {
        PossuiEstoqueSuficienteSpec spec = new PossuiEstoqueSuficienteSpec(Quantidade.of(150.0));
        
        assertThat(spec.satisfeitoPor(material)).isFalse();
    }

    @Test
    void deveVerificarEstoqueCritico() {
        EstaCriticamenteBaixoSpec spec = new EstaCriticamenteBaixoSpec(Quantidade.of(120.0));
        
        assertThat(spec.satisfeitoPor(material)).isTrue();
    }

    @Test
    void deveVerificarMaterialSemEstoque() {
        Material materialVazio = Material.builder()
            .nome("Material Vazio")
            .categoria("Teste")
            .unidadeMedida("UN")
            .quantidadeTotal(0.0)
            .build();
        
        EstaSemEstoqueSpec spec = new EstaSemEstoqueSpec();
        
        assertThat(spec.satisfeitoPor(materialVazio)).isTrue();
        assertThat(spec.satisfeitoPor(material)).isFalse();
    }

    @Test
    void deveCombinarSpecificationsComE() {
        PossuiEstoqueSuficienteSpec temEstoque = new PossuiEstoqueSuficienteSpec(Quantidade.of(50.0));
        EstaCriticamenteBaixoSpec estaCritico = new EstaCriticamenteBaixoSpec(Quantidade.of(10.0));
        
        var combinada = temEstoque.e(estaCritico.nao());
        
        assertThat(combinada.satisfeitoPor(material)).isTrue();
    }

    @Test
    void deveCombinarSpecificationsComOu() {
        EstaCriticamenteBaixoSpec estaCritico = new EstaCriticamenteBaixoSpec(Quantidade.of(10.0));
        EstaSemEstoqueSpec semEstoque = new EstaSemEstoqueSpec();
        
        var combinada = estaCritico.ou(semEstoque);
        
        assertThat(combinada.satisfeitoPor(material)).isFalse();
    }

    @Test
    void deveNegarSpecification() {
        EstaSemEstoqueSpec semEstoque = new EstaSemEstoqueSpec();
        
        var negada = semEstoque.nao();
        
        assertThat(negada.satisfeitoPor(material)).isTrue();
    }
}
