package com.example;

import com.example.repositories.ProdutoRepository;
import com.example.models.Produto;

import java.util.Scanner;

public class Comercio {

    static Produto[] produtosCadastrados = ProdutoRepository.buscarProdutos();
    static Scanner teclado;

    static void listarTodosOsProdutos() {

        for ( int i = 0 ; i < produtosCadastrados.length ; i ++ ) {

            if ( produtosCadastrados[i] != null ) break;

            if ( i == produtosCadastrados.length - 1 && produtosCadastrados[i] == null ) {

                System.out.println("Nenhum produto cadastrado.");
                return;

            }

        }

        System.out.println("+---------------------------+--------------+--------+------------------+--------------+");
        System.out.println("| Descrição                 | Preço Custo  | Margem | Data de Validade | Valor Venda  |");
        System.out.println("+---------------------------+--------------+--------+------------------+--------------+");
                          
        for ( int i = 0 ; i < produtosCadastrados.length ; i ++ ) if ( produtosCadastrados[i] != null ) produtosCadastrados[i].toString();

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

        listarTodosOsProdutos();

    }

}