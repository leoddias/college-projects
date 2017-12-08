package com.octavio.appdeliverit.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.octavio.appdeliverit.Util.Estatico;

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

/**
 * Created by Octavio Sabatine Ranieri on 03/04/16.
 */
public class TipoProduto{

    private int id;
    private String tipoProduto;
    private RequestQueue requestQueue;
    private String selectTipoProduto = "http://"+ Estatico.ipServidor+"/WebServiceDeliverit/selecttipoproduto.php";
    private ArrayList<TipoProduto> lista;

    public TipoProduto(){
        this.id = -1;
        tipoProduto = "";
    }

    public ArrayList<TipoProduto> getLista(Context context){

        lista = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                selectTipoProduto, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray tiposProduto = response.getJSONArray("cad_categoria");
                    for(int i=0; i<tiposProduto.length();i++){

                        JSONObject tipoProduto = tiposProduto.getJSONObject(i);

                        TipoProduto obj = new TipoProduto();
                        obj.setId(tipoProduto.getInt("codCategoria"));
                        obj.setTipoProduto(tipoProduto.getString("nomeCategoria"));
                        lista.add(obj);
                        Log.d("Parser tipoproduto: ", obj.tipoProduto);
                        obj = null;
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

        return lista;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
