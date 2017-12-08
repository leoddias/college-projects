package br.pucpcaldas.inf.lc.deliverit.model;
public class Cliente {
    private int codCliente;
    private String nomeCliente;
    private String emailCliente;
    private String senhaCliente;
    private String enderecoCliente;
    
    public Cliente(int codCliente, String nomeCliente, String emailCliente, String senhaCliente, String enderecoCliente){
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
        this.enderecoCliente = enderecoCliente;                
    }
    
    public Cliente(int codCliente, String nomeCliente, String enderecoCliente)
    {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;                
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }
    
    
    
}
