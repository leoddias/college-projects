package com.octavio.appdeliverit.Model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.octavio.appdeliverit.Util.Estatico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mac on 03/04/16.
 */
public class Produto{

    private int id;
    private String nomeProduto;
    private float preco;
    private int qtdEstoque;
    private int codCategoria;
    private String urlWeb;
    private String urlMobile;
    private RequestQueue requestQueue;
    private String selectProduto;
    private ArrayList<Produto> lista;

    public Produto(){
        super();
        this.id = -1;
        this.nomeProduto = "";
    }

    public ArrayList<Produto> getLista(Context context,String categoria){
        lista = new ArrayList<>();
        selectProduto = "http://"+ Estatico.ipServidor+"/WebServiceDeliverit/selectprodutoporcategoria.php?cat="+categoria+"";
        requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                selectProduto, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray produtos = response.getJSONArray("cad_produto");
                    for(int i=0; i<produtos.length();i++){

                        JSONObject produto = produtos.getJSONObject(i);

                        Produto obj = new Produto();

                        obj.setId(produto.getInt("codProduto"));
                        obj.setNomeProduto(produto.getString("nomeProduto"));
                        obj.setPreco((float) produto.getDouble("precoProduto"));
                        obj.setQtdEstoque(produto.getInt("qtdEstoque"));
                        obj.setCodCategoria(produto.getInt("codCategoria"));
                        obj.setUrlWeb(produto.getString("urlWeb"));
                        obj.setUrlMobile(produto.getString("urlMobile"));
                        lista.add(obj);
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getUrlMobile() {
        return urlMobile;
    }

    public void setUrlMobile(String urlMobile) {
        this.urlMobile = urlMobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }


}
