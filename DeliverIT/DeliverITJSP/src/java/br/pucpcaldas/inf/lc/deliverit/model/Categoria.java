package br.pucpcaldas.inf.lc.deliverit.model;

public class Categoria {
    private String nome = "UNKNOW";
    private int codigo;
     
    public Categoria(String nome, int codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }
    
}
