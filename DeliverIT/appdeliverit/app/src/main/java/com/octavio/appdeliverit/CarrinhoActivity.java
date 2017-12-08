package com.octavio.appdeliverit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sourcey.appdeliverit.R;
import com.octavio.appdeliverit.Util.Estatico;

/**
 * Created by Octavio on 14/05/16.
 */
public class CarrinhoActivity extends AppCompatActivity {

    private EditText numero;
    private EditText endereco;
    private TextView labelRua;
    private TextView labelNumero;
    private RadioGroup radioGroup;
    private AppCompatButton botaoFinalizaCompra;
    private AppCompatButton botaoCalculaFrete;
    private int tipoEntrega = 1;
    private final int LOCAL_ATUAL = 1;
    private final int LOCAL_ENDERECO = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        radioGroup = (RadioGroup) findViewById(R.id.radioLocalEntrega);
        endereco = (EditText) findViewById(R.id.campoTextoEnd);
        numero = (EditText) findViewById(R.id.campoTextoNumero);
        labelRua = (TextView) findViewById(R.id.labelEndereco);
        labelNumero = (TextView) findViewById(R.id.labelNumero);
        botaoFinalizaCompra = (AppCompatButton) findViewById(R.id.finalizaCompra);
        botaoCalculaFrete = (AppCompatButton) findViewById(R.id.calcula);

        setVisibilidade(false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if(id == R.id.radioLocalAtual) {
                    tipoEntrega = LOCAL_ATUAL;
                    setVisibilidade(false);
                }
                else if(id==R.id.radioEndereco) {
                    tipoEntrega = LOCAL_ENDERECO;
                    setVisibilidade(true);
                }
            }
        });

        botaoCalculaFrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String end="";
                if(tipoEntrega == LOCAL_ATUAL){
                    end = Estatico.getLocal();
                }
                else if (tipoEntrega == LOCAL_ENDERECO){
                    end = endereco.getText()+" "+numero.getText();
                }
                Toast.makeText(getApplicationContext(),end,Toast.LENGTH_SHORT).show();
                //TODO:Metodo calcula frete
            }
        });

        botaoFinalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Finalizou pedido!",Toast.LENGTH_LONG).show();
                //TODO:Insere pedido
            }
        });

    }

    private void setVisibilidade(boolean visivel){
        int estado;
        if (visivel)
            estado = View.VISIBLE;
        else
            estado = View.INVISIBLE;

        endereco.setVisibility(estado);
        numero.setVisibility(estado);
        labelNumero.setVisibility(estado);
        labelRua.setVisibility(estado);
    }
}
