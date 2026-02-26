package com.example.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoPerecivelTest {

    @Test
    void testValorVendaSemDesconto() {

        LocalDate dataValidade = LocalDate.now().plusDays(10);
        ProdutoPerecivel produto = new ProdutoPerecivel("Leite", 4.0, 0.25, dataValidade);
        
        assertEquals(5.0, produto.valorVenda(), 0.01);

    }

    @Test
    void testValorVendaComDesconto() {

        LocalDate dataValidade = LocalDate.now().plusDays(5);
        ProdutoPerecivel produto = new ProdutoPerecivel("Iogurte", 3.0, 0.2, dataValidade);
        
        // Valor base: 3.0 * 1.2 = 3.6
        // Com desconto de 25%: 3.6 * 0.75 = 2.7
        assertEquals(2.7, produto.valorVenda(), 0.01);

    }

    @Test
    void testValorVendaComMargemPadrao() {

        LocalDate dataValidade = LocalDate.now().plusDays(10);
        ProdutoPerecivel produto = new ProdutoPerecivel("Queijo", 8.0, dataValidade);
        
        // Margem padrão é 0.2 (20%)
        assertEquals(9.6, produto.valorVenda(), 0.01);

    }

    @Test
    void testProdutoVencidoLancaExcecao() {

        LocalDate dataVencida = LocalDate.now().minusDays(1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            new ProdutoPerecivel("Produto Vencido", 5.0, 0.2, dataVencida);
        });
        
    }

    @Test
    void testToStringContemDataValidade() {

        LocalDate dataValidade = LocalDate.now().plusDays(7);
        ProdutoPerecivel produto = new ProdutoPerecivel("Manteiga", 6.0, 0.3, dataValidade);
        String resultado = produto.toString();
        
        assertTrue(resultado.contains("Manteiga"));
        assertTrue(resultado.contains(dataValidade.toString()));

    }

    @Test
    void testDescontoNoLimiteDosPrazos() {

        // Exatamente 7 dias para vencer - deve ter desconto
        LocalDate dataValidade = LocalDate.now().plusDays(7);
        ProdutoPerecivel produto = new ProdutoPerecivel("Presunto", 10.0, 0.2, dataValidade);
        
        // Valor base: 10 * 1.2 = 12
        // Com desconto: 12 * 0.75 = 9
        assertEquals(9.0, produto.valorVenda(), 0.01);
        
    }
}
