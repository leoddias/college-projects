package br.pucpcaldas.inf.lc.deliverit.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class FluxoCaixa {
    private String descricao = "UNKNOW";
    
    private Timestamp data;
    
    private boolean movimentacao;
    private float valor;
     
    public FluxoCaixa(String descricao, Timestamp data, boolean movimentacao, float valor){
        this.descricao = descricao;
        this.data = data;
        this.movimentacao = movimentacao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getData()
    {
        Date date = new Date(data.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }

    public boolean getMovimentacao() {
        return movimentacao;
    }
    
    public float getValor() {
        return valor;
    }
    
}
