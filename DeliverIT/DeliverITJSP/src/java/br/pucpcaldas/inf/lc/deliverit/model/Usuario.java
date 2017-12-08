package br.pucpcaldas.inf.lc.deliverit.model;

public class Usuario {
    private String nome = "UNKNOW";
    private int tipo;
     
    public Usuario(String name, int tipo){
        this.nome = name;
        this.tipo = tipo;
    }

    public String getName() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }
    
}
