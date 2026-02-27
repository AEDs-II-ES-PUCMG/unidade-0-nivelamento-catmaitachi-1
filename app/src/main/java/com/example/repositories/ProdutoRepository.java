package com.example.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Produto;
import com.example.services.ProdutoService;

public class ProdutoRepository {

    static final int MAX_NOVOS_PRODUTOS = 10;
    static String nomeArquivoDados = "../produtos.txt";
    static int quantosProdutos;
    
    public static void salvarProduto( Produto[] produtos ) {

        for ( int i = 0; i < produtos.length; i ++ ) {

            String linha = produtos[i].gerarDadosTexto();

            try ( BufferedWriter writer = new BufferedWriter( new FileWriter(nomeArquivoDados, false) ) ) {

                writer.write(linha);
                writer.newLine();

            } catch ( IOException e ) { throw new RuntimeException("Erro ao salvar o produto.", e); }

        }

    }

    public static Produto[] buscarProdutos() {

        try ( BufferedReader reader = new BufferedReader( new FileReader(nomeArquivoDados) ) ) {

            List<Produto> lista = new ArrayList<>();
            String linha;

            while ( (linha = reader.readLine()) != null ) {

                Produto produto = ProdutoService.criarDoTexto(linha);
                lista.add(produto);

            }

            quantosProdutos = lista.size();

            Produto[] produtos = new Produto[ quantosProdutos + MAX_NOVOS_PRODUTOS ];

            for ( int i = 0; i < quantosProdutos; i ++ ) produtos[i] = lista.get(i); 

            return produtos;

        } catch ( FileNotFoundException e ) {

            return new Produto[MAX_NOVOS_PRODUTOS];
        
        } catch ( IOException e ) { 
            
            quantosProdutos = 0;

            throw new RuntimeException("Erro ao buscar produtos.", e);

        }

    }

}
