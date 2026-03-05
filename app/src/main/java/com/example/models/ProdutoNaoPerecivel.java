package com.example.models;

public class ProdutoNaoPerecivel extends Produto {

    public ProdutoNaoPerecivel ( String desc, double precoCusto, double margemLucro, int estoque ) {

        super(desc, precoCusto, margemLucro, estoque);

    }

    public ProdutoNaoPerecivel ( String desc, double precoCusto, int estoque ) {

        super(desc, precoCusto, estoque);

    }

    @Override
    public String gerarDadosTexto() {

        return "1;" + this.getDescricao() + ";" + this.getPrecoCusto() + ";" + this.getMargemLucro() + ";" + this.getEstoque(); 

    }
    
}
