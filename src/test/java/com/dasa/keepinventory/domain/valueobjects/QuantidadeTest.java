package com.dasa.keepinventory.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class QuantidadeTest {

    @Test
    void deveCriarQuantidadeValida() {
        Quantidade quantidade = Quantidade.of(10.0);
        
        assertThat(quantidade.getValor()).isEqualTo(10.0);
    }

    @Test
    void deveLancarExcecaoParaQuantidadeNegativa() {
        assertThatThrownBy(() -> Quantidade.of(-5.0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("não pode ser negativa");
    }

    @Test
    void deveLancarExcecaoParaQuantidadeNula() {
        assertThatThrownBy(() -> Quantidade.of(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("não pode ser negativa");
    }

    @Test
    void deveAdicionarQuantidades() {
        Quantidade q1 = Quantidade.of(10.0);
        Quantidade q2 = Quantidade.of(5.0);
        
        Quantidade resultado = q1.adicionar(q2);
        
        assertThat(resultado.getValor()).isEqualTo(15.0);
    }

    @Test
    void deveSubtrairQuantidades() {
        Quantidade q1 = Quantidade.of(10.0);
        Quantidade q2 = Quantidade.of(3.0);
        
        Quantidade resultado = q1.subtrair(q2);
        
        assertThat(resultado.getValor()).isEqualTo(7.0);
    }

    @Test
    void deveLancarExcecaoAoSubtrairResultandoEmNegativo() {
        Quantidade q1 = Quantidade.of(5.0);
        Quantidade q2 = Quantidade.of(10.0);
        
        assertThatThrownBy(() -> q1.subtrair(q2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("não pode ser negativa");
    }

    @Test
    void deveCompararQuantidades() {
        Quantidade q1 = Quantidade.of(10.0);
        Quantidade q2 = Quantidade.of(5.0);
        
        assertThat(q1.menorQue(q2)).isFalse();
        assertThat(q2.menorQue(q1)).isTrue();
        assertThat(q1.maiorOuIgualA(q2)).isTrue();
    }

    @Test
    void deveSerIgualQuandoValoresIguais() {
        Quantidade q1 = Quantidade.of(10.0);
        Quantidade q2 = Quantidade.of(10.0);
        
        assertThat(q1).isEqualTo(q2);
        assertThat(q1.hashCode()).isEqualTo(q2.hashCode());
    }
}
