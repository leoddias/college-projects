package br.pucpcaldas.inf.lc.deliverit.model;

public class Produto {
    private int codProduto;
    private String nomeProduto;
    private int qtdProduto;
    private int qtdEstoque;
    private float precoProduto;
    private int codCategoria;

    public Produto(String nomeProduto, int qtdEstoque, float precoProduto, int codCategoria) {
        
        this.nomeProduto = nomeProduto;
        this.qtdEstoque = qtdEstoque;
        this.precoProduto = precoProduto;
        this.codCategoria = codCategoria;        
    }
    
    public Produto(int codProduto, String nomeProduto, int qtdEstoque, float precoProduto, int codCategoria) {
        this.codProduto = codProduto;
        this.nomeProduto = nomeProduto;
        this.qtdEstoque = qtdEstoque;
        this.precoProduto = precoProduto;
        this.codCategoria = codCategoria;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }
    
   
    
}
