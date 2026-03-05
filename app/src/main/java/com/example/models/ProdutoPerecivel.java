package com.example.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ProdutoPerecivel extends Produto {

    private static final double DESCONTO = 0.25;
    private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;
    
    public ProdutoPerecivel ( String desc, double precoCusto, double margemLucro, int estoque, LocalDate validade ) {

        super(desc, precoCusto, margemLucro, estoque);
        this.dataDeValidade = validade;
        
        if ( estaVencido() ) throw new IllegalArgumentException("Produto já está vencido!");

    }

    public ProdutoPerecivel ( String desc, double precoCusto, int estoque, LocalDate validade ) {

        super(desc, precoCusto, estoque);
        this.dataDeValidade = validade;

        if ( estaVencido() ) throw new IllegalArgumentException("Produto já está vencido!");

    }

    private boolean estaVencido() { return dataDeValidade.isBefore(LocalDate.now()); }

    @Override
    public double valorVenda() {

        double valorBase = precoCusto * ( 1 + margemLucro );
        long diasParaVencer = ChronoUnit.DAYS.between(LocalDate.now(), dataDeValidade);

        if ( diasParaVencer > PRAZO_DESCONTO ) return valorBase; 

        return valorBase * ( 1 - DESCONTO );

    }

    @Override
    public String gerarDadosTexto() {

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",", ".");
        String dataFormatada = dataDeValidade.format(formatoData);

        return String.format("2;%s;%s;%s;%d;%s", this.getDescricao(), precoFormatado, margemFormatada, this.getEstoque(), dataFormatada);
    
    }

    @Override
    public String toString() {

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataDeValidade.format(formatoData);

        return String.format(
            
              "| %-25s | R$ %9.2f | %5.2f%% | %-16s | R$ %9.2f | %-7d |%n"
            + "+---------------------------+--------------+--------+------------------+--------------+"

            , getDescricao(), precoCusto, margemLucro * 100, dataFormatada, valorVenda()
        );

    }

}