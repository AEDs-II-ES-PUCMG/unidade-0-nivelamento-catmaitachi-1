package com.example.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoNaoPerrecivelTest {

    @Test
    void testValorVendaComMargemPersonalizada() {

        ProdutoNaoPerecivel produto = new ProdutoNaoPerecivel("Arroz 5kg", 20.0, 0.3);

        assertEquals(26.0, produto.valorVenda(), 0.01);

    }

    @Test
    void testValorVendaComMargemPadrao() {

        ProdutoNaoPerecivel produto = new ProdutoNaoPerecivel("Feijão 1kg", 10.0);
        
        assertEquals(12.0, produto.valorVenda(), 0.01);

    }

    @Test
    void testGetDescricao() {

        ProdutoNaoPerecivel produto = new ProdutoNaoPerecivel("Macarrão", 5.0);
        
        assertEquals("Macarrão", produto.getDescricao());

    }

    @Test
    void testSetDescricao() {

        ProdutoNaoPerecivel produto = new ProdutoNaoPerecivel("Produto A", 10.0);
        produto.setDescricao("Produto B");
        
        assertEquals("Produto B", produto.getDescricao());

    }

    @Test
    void testToStringContemDescricao() {

        ProdutoNaoPerecivel produto = new ProdutoNaoPerecivel("Sal 1kg", 2.0, 0.15);
        String resultado = produto.toString();
        
        assertTrue(resultado.contains("Sal 1kg"));
        
    }
}
