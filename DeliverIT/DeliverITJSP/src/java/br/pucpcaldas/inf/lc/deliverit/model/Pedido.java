package br.pucpcaldas.inf.lc.deliverit.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Pedido {

    private int codPedido;
    private float valorTotal;
    private float valorEntrega;
    private float valorDesconto;
    private int codCliente;
    private int codEstabelecimento;
    private Timestamp dataPedido;
    private String statusPedido;

    public Pedido(int codPedido, float valorTotal, float valorEntrega, float valorDesconto,
            int codCliente, int codEstabelecimento, Timestamp dataPedido, String statusPedido) {
        this.codPedido = codPedido;
        this.valorTotal = valorTotal;
        this.valorEntrega = valorEntrega;
        this.valorDesconto = valorDesconto;
        this.codCliente = codCliente;
        this.codEstabelecimento = codEstabelecimento;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;

    }

    public String getDataPedidoStr() {
        Date date = new Date(dataPedido.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a - dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }

    public int getCodPedido() {
        return codPedido;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public float getValorEntrega() {
        return valorEntrega;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public int getCodEstabelecimento() {
        return codEstabelecimento;
    }

    public Timestamp getDataPedido() {
        return dataPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

}
