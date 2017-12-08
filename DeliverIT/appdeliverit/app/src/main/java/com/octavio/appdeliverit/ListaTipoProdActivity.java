package com.octavio.appdeliverit;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.octavio.appdeliverit.Adapter.TipoProdutoAdapter;
import com.octavio.appdeliverit.Model.TipoProduto;
import com.octavio.appdeliverit.Util.Estatico;
import com.sourcey.appdeliverit.R;

import android.widget.AdapterView.OnItemClickListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ListaTipoProdActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_prod);


        listView = (ListView) findViewById(R.id.listViewTipoProd);
        Log.d("ListaTipoProdActivity", "onCreate");


        TipoProdutoAdapter adapter = new TipoProdutoAdapter(this,new TipoProduto().getLista(getApplicationContext()));
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new ListClickHandler());
    }


    public class ListClickHandler implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            TipoProduto obj = (TipoProduto) adapter.getItemAtPosition(position);

            //Toast.makeText(getApplicationContext(),"Clicou",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),obj.getTipoProduto(),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(ListaTipoProdActivity.this, ListaProdActivity.class);

            intent.putExtra("categoria", obj.getTipoProduto());
            startActivity(intent);
        }
    }


}


