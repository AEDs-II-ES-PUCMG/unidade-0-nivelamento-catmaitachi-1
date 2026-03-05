package com.example.repositories;

import com.example.models.ItemDePedido;
import com.example.models.Pedido;

public class PedidoRepository {
    
    public static void processarPedido( Pedido p ) {

        ItemDePedido[] itens = p.getItems();

        for ( int i = 0 ; i < p.getQuantProdutos() ; i ++ ) {

            itens[i].getProduto().baixaEstoque(itens[i].getQuantidade());

        }

    }

}
