package com.example.models;

public class ProdutoNaoPerecivel extends Produto {

    public ProdutoNaoPerecivel ( String desc, double precoCusto, double margemLucro ) {

        super(desc, precoCusto, margemLucro);

    }

    public ProdutoNaoPerecivel ( String desc, double precoCusto ) {

        super(desc, precoCusto);

    }

    @Override
    public String gerarDadosTexto() {

        return "1;" + this.getDescricao() + ";" + this.getPrecoCusto() + ";" + this.getMargemLucro();

    }
    
}
