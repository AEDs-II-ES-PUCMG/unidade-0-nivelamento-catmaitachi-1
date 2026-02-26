package com.example.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public boolean equals(Object obj) {

        Produto outro = (Produto)obj;
        return this.descricao.toLowerCase().equals(outro.getDescricao().toLowerCase());

    }

    public Produto criarDoTexto(String linha) {

        Produto novoProduto = null;

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String[] atributos = linha.split(";");

        int tipo = Integer.parseInt(atributos[0]);
        String descricao = atributos[1];
        double precoCusto = Double.parseDouble(atributos[2]);
        double margemLucro = Double.parseDouble(atributos[3]);

        if ( tipo == 1 ) novoProduto = new ProdutoNaoPerecivel(descricao, precoCusto, margemLucro);

        else {

            LocalDate dataValidade = LocalDate.parse(atributos[4], formatoData);
            novoProduto = new ProdutoPerecivel(descricao, precoCusto, margemLucro, dataValidade);

        }
        
        return novoProduto;

    }

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