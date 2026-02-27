package com.example.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ProdutoPerecivel extends Produto {

    private static final double DESCONTO = 0.25;
    private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;
    
    public ProdutoPerecivel ( String desc, double precoCusto, double margemLucro, LocalDate validade ) {

        super(desc, precoCusto, margemLucro);
        this.dataDeValidade = validade;

        if ( estaVencido() ) throw new IllegalArgumentException("Produto já está vencido!");

    }

    public ProdutoPerecivel ( String desc, double precoCusto, LocalDate validade ) {

        super(desc, precoCusto);
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

        return "2;" + this.getDescricao() + ";" + this.getPrecoCusto() + ";" + this.getMargemLucro() + ";" + this.dataDeValidade.format(formatoData);
    
    }

    @Override
    public String toString() {

        return String.format(
            
              "+---------------------------+--------------+--------+------------------+--------------+%n"
            + "| Descrição                 | Preço Custo  | Margem | Data de Validade | Valor Venda  |%n"
            + "+---------------------------+--------------+--------+------------------+--------------+%n"
            + "| %-25s | R$ %9.2f | %5.2f%% | %-16s | R$ %9.2f |%n"
            + "+---------------------------+--------------+--------+------------------+--------------+"

            , getDescricao(), precoCusto, margemLucro * 100, dataDeValidade, valorVenda()
        );

    }

}