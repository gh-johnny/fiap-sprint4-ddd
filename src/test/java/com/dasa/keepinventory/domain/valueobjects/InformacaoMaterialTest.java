package com.dasa.keepinventory.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class InformacaoMaterialTest {

    @Test
    void deveCriarInformacaoMaterialValida() {
        InformacaoMaterial info = InformacaoMaterial.of(
            "Luvas de Procedimento",
            "EPI",
            "UN"
        );
        
        assertThat(info.getNome()).isEqualTo("Luvas de Procedimento");
        assertThat(info.getCategoria()).isEqualTo("EPI");
        assertThat(info.getUnidadeMedida().getValor()).isEqualTo("UN");
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        assertThatThrownBy(() -> InformacaoMaterial.of("", "EPI", "UN"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Nome");
    }

    @Test
    void deveLancarExcecaoParaNomeNulo() {
        assertThatThrownBy(() -> InformacaoMaterial.of(null, "EPI", "UN"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Nome");
    }

    @Test
    void deveAceitarCategoriaNula() {
        InformacaoMaterial info = InformacaoMaterial.of("Material", null, "UN");
        
        assertThat(info.getCategoria()).isNull();
    }

    @Test
    void deveRemoverEspacosEmBranco() {
        InformacaoMaterial info = InformacaoMaterial.of(
            "  Luvas  ",
            "  EPI  ",
            "UN"
        );
        
        assertThat(info.getNome()).isEqualTo("Luvas");
        assertThat(info.getCategoria()).isEqualTo("EPI");
    }
}
