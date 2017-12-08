package com.octavio.appdeliverit;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.octavio.appdeliverit.Adapter.ProdutoCarrinhoAdapter;
import com.octavio.appdeliverit.Model.Produto;
import com.sourcey.appdeliverit.R;
import com.octavio.appdeliverit.Util.Estatico;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    AppCompatButton botaoFinaliza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton botaoAddProduto = (FloatingActionButton) findViewById(R.id.fab);
        botaoFinaliza = (AppCompatButton) findViewById(R.id.finaliza);
        verificaBotao();

        listView = (ListView) findViewById(R.id.listViewCarrinho);

        ProdutoCarrinhoAdapter adapter = new ProdutoCarrinhoAdapter(this, Estatico.carrinho);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListClickHandler());

        botaoAddProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListaTipoProdActivity.class));
            }
        });

        botaoFinaliza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        verificaBotao();
                        startActivity(new Intent(getApplicationContext(), CarrinhoActivity.class));
                }
        });
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {


            Produto obj = (Produto) adapter.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),"Produto removido do carrinho!",Toast.LENGTH_LONG).show();
            Estatico.carrinho.remove(obj);

            ProdutoCarrinhoAdapter adaptador = new ProdutoCarrinhoAdapter(getApplicationContext(), Estatico.carrinho);
            listView.setAdapter(adaptador);
            verificaBotao();

        }
    }

    public void verificaBotao(){
        if(Estatico.carrinho.size()>0)
            botaoFinaliza.setVisibility(View.VISIBLE);
        else
            botaoFinaliza.setVisibility(View.INVISIBLE);
    }
}
