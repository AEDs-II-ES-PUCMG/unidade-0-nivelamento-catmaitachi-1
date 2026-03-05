package com.example;

import com.example.repositories.ProdutoRepository;
import com.example.models.Produto;
import com.example.models.ProdutoNaoPerecivel;
import com.example.models.ProdutoPerecivel;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Scanner;

// Teste de commit

public class Comercio {

    static Produto[] produtosCadastrados;
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
                          
        for ( int i = 0 ; i < produtosCadastrados.length ; i ++ ) if ( produtosCadastrados[i] != null ) System.out.println(produtosCadastrados[i].toString());

    }

    static void localizarProduto() {

        System.out.print("\nDigite a descrição do Produto: ");

        String desc = teclado.nextLine();
        
        for ( int i = 0 ; i < produtosCadastrados.length ; i ++ ) if ( produtosCadastrados[i] != null && produtosCadastrados[i].getDescricao().toLowerCase().contains(desc.toLowerCase()) ) {

            System.out.println("+---------------------------+--------------+--------+------------------+--------------+");
            System.out.println("| Descrição                 | Preço Custo  | Margem | Data de Validade | Valor Venda  |");
            System.out.println("+---------------------------+--------------+--------+------------------+--------------+");

            System.out.println(produtosCadastrados[i].toString());

            return;

        }  

    }

    static void cadastrarProduto() {

        for ( int i = 0 ; i < produtosCadastrados.length ; i ++ ) if ( produtosCadastrados[i] == null ) {

            try {

            System.out.print("\nDigite a descrição do Produto: ");
            String desc = teclado.nextLine();

            System.out.print("\nDigite o preço de custo do Produto: ");
            double precoCusto = Double.parseDouble(teclado.nextLine());

            System.out.print("\nDigite a margem de lucro desejada para o Produto (em %), ou será aplicado o padrão de 20%: ");
            String margemInput = teclado.nextLine();
            double margemLucro = margemInput.isEmpty() ? Produto.getMargemPadrao() : Double.parseDouble(margemInput) / 100;

            System.out.print("\nPara produto perecível, digite a data de validade (dd/MM/yyyy), ou deixe em branco para produto não perecível: ");
            String validadeInput = teclado.nextLine();

            if ( validadeInput.isEmpty() ) {

                produtosCadastrados[i] = new ProdutoNaoPerecivel(desc, precoCusto, margemLucro );

            } else {

                String[] data = validadeInput.split("/");

                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                int ano = Integer.parseInt(data[2]);

                LocalDate validade = LocalDate.of(ano, mes, dia);

                produtosCadastrados[i] = new ProdutoPerecivel(desc, precoCusto, margemLucro, validade);

            }

            } catch ( IllegalArgumentException e ) {  
                
                produtosCadastrados[i] = null; 
                i--;

                System.err.println( "\n[ Erro ] " + e.getMessage()); 
            
            }

            System.out.print("\nDeseja cadastrar mais um produto? ( s/N ): ");

            String resposta = teclado.nextLine();

            if ( resposta.equalsIgnoreCase("s") ) continue;
            else {

                produtosCadastrados = ProdutoRepository.verificarRepetido(produtosCadastrados);
                return;

            }

        } 

        System.out.println();
        System.out.println("Não é possível cadastrar mais produtos num momento.");

    }
    
    static void pausa() {

        System.out.println();
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
        System.out.print("\nDigite sua opção: ");

        return Integer.parseInt(teclado.nextLine());

    }

    public static void main(String[] args) {

        teclado = new Scanner(System.in, Charset.forName("ISO-8859-2"));
        produtosCadastrados = ProdutoRepository.buscarProdutos();

        int opcao = -1;
        
        do {

            opcao = menu();

            switch (opcao) {

                case 1: listarTodosOsProdutos(); 
                
                    break;

                case 2: localizarProduto();

                    break;

                case 3: cadastrarProduto();

                    break;
            
                default: break;
            }

            pausa();

        } while ( opcao != 0 );

        ProdutoRepository.salvarProduto(produtosCadastrados);

    }

}