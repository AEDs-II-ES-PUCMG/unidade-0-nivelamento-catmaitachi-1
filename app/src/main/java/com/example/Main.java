package com.example;

import com.example.models.Produto;
import com.example.models.ProdutoNaoPerecivel;
import com.example.models.ProdutoPerecivel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println(">> Cadastro de Produtos <<\n");
        
        System.out.println("Escolha uma categoria:\n");
        System.out.println("[ 1 ] Produto Perecível");
        System.out.println("[ 2 ] Produto Não Perecível");
        System.out.print("\n: ");
        
        int input = scanner.nextInt();
        scanner.nextLine(); 

        try {

            Produto produto = null;
            
            System.out.print("\n>> Descrição: ");
            String descricao = scanner.nextLine();
            
            System.out.print(">> Preço de custo: R$ ");
            double precoCusto = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print(">> Margem de lucro ( Padrão 20% ): ");
            String margemStr = scanner.nextLine();
            
            if (input == 1) {

                System.out.print(">> Data de validade ( dd/MM/yyyy ): ");
                String dataStr = scanner.nextLine();
                LocalDate dataValidade = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                if ( margemStr.isEmpty() ) produto = new ProdutoPerecivel(descricao, precoCusto, dataValidade);
                else {

                    double margem = Double.parseDouble(margemStr) / 100;
                    produto = new ProdutoPerecivel(descricao, precoCusto, margem, dataValidade);
                
                }
                
            } else if (input == 2) {
                
                if (margemStr.isEmpty()) produto = new ProdutoNaoPerecivel(descricao, precoCusto);
                else {

                    double margem = Double.parseDouble(margemStr) / 100;
                    produto = new ProdutoNaoPerecivel(descricao, precoCusto, margem);
                
                }
                
            } else {

                System.out.println("\nOpção inválida!");
                scanner.close();
                return;

            }
            
            System.out.println(produto);
            
        } catch ( IllegalArgumentException e ) { System.out.println("\n❌ ERRO: " + e.getMessage());
        } catch ( DateTimeParseException e ) { System.out.println("\n❌ ERRO: Data inválida! Use o formato dd/MM/yyyy");
        } catch ( Exception e ) { System.out.println("\n❌ ERRO: " + e.getMessage());
        } finally { scanner.close(); }

    }
}