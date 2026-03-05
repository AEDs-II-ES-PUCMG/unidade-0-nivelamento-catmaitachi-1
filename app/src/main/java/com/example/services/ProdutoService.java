package com.example.services;

import com.example.models.Produto;
import com.example.models.ProdutoNaoPerecivel;
import com.example.models.ProdutoPerecivel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoService {

    public static Produto criarDoTexto(String linha) {

        Produto novoProduto = null;

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String[] atributos = linha.split(";");

        int tipo = Integer.parseInt(atributos[0]);
        String descricao = atributos[1];
        double precoCusto = Double.parseDouble(atributos[2]);
        double margemLucro = Double.parseDouble(atributos[3]);
        int estoque = Integer.parseInt(atributos[4]);


        switch (tipo) {

            case 1:

                novoProduto = new ProdutoNaoPerecivel(descricao, precoCusto, margemLucro, estoque);
                break;

            case 2:
                
                LocalDate dataValidade = LocalDate.parse(atributos[5], formatoData);
                novoProduto = new ProdutoPerecivel(descricao, precoCusto, margemLucro, estoque, dataValidade);
                break;

            default: throw new IllegalArgumentException("Tipo de produto inválido!");
        
        }

        return novoProduto;

    }
    
}
