package com.example.models;

public class Produto {

    private static final double MARGEM_PADRAO = 0.2;
    protected double margemLucro;
    protected double precoCusto;
    private String descricao;

    protected Produto ( String desc, double precoCusto, double margemLucro ) {

        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;

    }

    protected Produto ( String desc, double precoCusto ) {

        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = MARGEM_PADRAO;

    }

    public String getDescricao() { return descricao; }
    public void setDescricao( String desc ) { descricao = desc; }

    public double valorVenda() { return precoCusto * ( 1 + margemLucro ); }

    @Override
    public String toString() {

        return String.format(

              "+---------------------------+--------------+--------+--------------+%n"
            + "| Descrição                 | Preço Custo  | Margem | Valor Venda  |%n"
            + "+---------------------------+--------------+--------+--------------+%n"
            + "| %-25s | R$ %9.2f | %5.2f%% | R$ %9.2f |%n"
            + "+---------------------------+--------------+--------+--------------+"

            , descricao, precoCusto, margemLucro * 100, valorVenda()
        
        );

    }

}