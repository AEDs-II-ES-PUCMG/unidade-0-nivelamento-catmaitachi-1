package com.example.models;

public class Produto {

    private static final double MARGEM_PADRAO = 0.2;
    protected double margemLucro;
    protected double precoCusto;
    private String descricao;
    private int estoque;

    protected Produto ( String desc, double precoCusto, double margemLucro, int estoque ) {

        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
        this.estoque = estoque;

    }

    protected Produto ( String desc, double precoCusto, int estoque ) {

        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = MARGEM_PADRAO;
        this.estoque = estoque;

    }

    public static double getMargemPadrao() { return MARGEM_PADRAO; }

    public int getEstoque() { return estoque; }
    public void setEstoque( int estoque ) { this.estoque = estoque; }
    public void baixaEstoque( int baixa ) { this.estoque -= baixa; }
    public void altaEstoque ( int alta ) { this.estoque += alta; }

    public String getDescricao() { return descricao; }
    public void setDescricao( String desc ) { descricao = desc; }

    public double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto( double precoCusto ) { this.precoCusto = precoCusto; }
  
    public double getMargemLucro() { return margemLucro; }
    public void setMargemLucro( double margemLucro ) { this.margemLucro = margemLucro; }

    public double valorVenda() { return precoCusto * ( 1 + margemLucro ); }

    public boolean equals(Object obj) {

        Produto produto = (Produto)obj;

        return this.getDescricao().toLowerCase().equals(produto.getDescricao().toLowerCase());

    }

    public boolean equals(String s) {

        return this.getDescricao().toLowerCase().equals(s.toLowerCase());

    }

    public String gerarDadosTexto() {

        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",", ".");

        return String.format("1;%s;%s;%d;%s", descricao, precoFormatado, estoque, margemFormatada);
    
    }

    @Override
    public String toString() {

        return String.format(

              "| %-25s | R$ %9.2f | %5.2f%% | %-16s | R$ %9.2f | %-7d | %n"
            + "+---------------------------+--------------+--------+------------------+--------------+---------+"

            , descricao, precoCusto, margemLucro * 100, null, valorVenda(), estoque
        
        );

    }

}