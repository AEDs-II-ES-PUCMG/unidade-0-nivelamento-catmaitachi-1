package com.example;

import com.example.repositories.ProdutoRepository;
import com.example.models.Produto;

import java.util.Scanner;

public class Comercio {

    static Produto[] produtosCadastrados = ProdutoRepository.buscarProdutos();
    static Scanner teclado;

    static void listarTodosOsProdutos() {

        System.out.println("+---------------------------+--------------+--------+------------------+--------------+%n");
        System.out.println("| Descrição                 | Preço Custo  | Margem | Data de Validade | Valor Venda  |%n");
        System.out.println("+---------------------------+--------------+--------+------------------+--------------+%n");
                          
        for ( int i = 0 ; i < produtosCadastrados.length ; i ++ ) produtosCadastrados[i].toString();

    }
    
    static void pausa() {

        System.out.println("Digite enter para continuar...");
        teclado.nextLine();

    }

    static void cabecalho() {

        System.out.println(">> AEDs II - Comércio de Coisinhas");
        System.out.println();

    }

    static int menu() {

        cabecalho();

        System.out.println("[ 1 ] Listar todos os produtos");
        System.out.println("[ 2 ] Procurar e listar um produto");
        System.out.println("[ 3 ] Cadastrar novo produto");
        System.out.println("[ 0 ] Sair");
        System.out.print("Digite sua opção: ");

        return Integer.parseInt(teclado.nextLine());

    }

    public static void main(String[] args) {


    }

}