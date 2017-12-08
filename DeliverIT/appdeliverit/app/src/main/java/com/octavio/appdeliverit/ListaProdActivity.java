package com.octavio.appdeliverit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.octavio.appdeliverit.Adapter.ProdutoAdapter;
import com.octavio.appdeliverit.Model.Produto;
import com.sourcey.appdeliverit.R;
import com.octavio.appdeliverit.Util.Estatico;


public class ListaProdActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prod);
        Log.d("ListaProdActivity", "onCreate");

        ListView listView = (ListView) findViewById(R.id.listViewProdutos);
        Intent intent = getIntent();
        String categoria = intent.getStringExtra("categoria");

        ProdutoAdapter adapter = new ProdutoAdapter(this,new Produto().getLista(getApplicationContext(), categoria));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListClickHandler());

    }
    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

            Produto obj = (Produto) adapter.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),obj.getNomeProduto(),Toast.LENGTH_LONG).show();
            Estatico.carrinho.add(obj);
            Log.d("Size carrinho: ", String.valueOf(Estatico.carrinho.size()));
            startActivity(new Intent(ListaProdActivity.this,MainActivity.class));
        }
    }

}
