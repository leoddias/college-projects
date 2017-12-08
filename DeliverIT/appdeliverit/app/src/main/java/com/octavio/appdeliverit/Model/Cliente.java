package com.octavio.appdeliverit.Model;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.octavio.appdeliverit.Util.Estatico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Octavio Sabatine Ranieri on 27/03/2016.
 */
public class Cliente  {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String local;
    private boolean retorno;

    public Cliente(){
        super();
        this.id= -1;
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.local = "";
    }


    public boolean login(final String email, final String senha, final Context context) {
        retorno = true;
        String urlLogin = "http://"+ Estatico.ipServidor+"/webservicedeliverit/login.PHP?email="+email+"&senha="+senha+"";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                        urlLogin, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray clientes = response.getJSONArray("cad_cliente");
                            Log.d("Try login", "Try, length: "+clientes.length());
                            if (clientes.length() == 1)
                                retorno = true;
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
        Log.d("Login", "retorno "+retorno);
        return retorno;
    }

    public void cadastraCliente(Cliente obj,Context context){
        String urlInsere = "http://"+ Estatico.ipServidor+"/webservicedeliverit/insertCliente.PHP?"+
                "nome="+obj.getNome()+
                "&email="+obj.getEmail()+""+
                "&senha="+obj.getSenha()+""+
                "&local="+obj.getLocal()+""+
                "&telefone="+obj.getTelefone();


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(StringRequest.Method.POST,
                urlInsere, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){};

        requestQueue.add(request);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean verificaLogin(String email,Context context){
        String urlLogin = "http://"+ Estatico.ipServidor+"/webservicedeliverit/login.PHP?email="+email;
        retorno = true;
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.POST,
                urlLogin, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray clientes = response.getJSONArray("cad_cliente");
                    if (clientes.length() == 1)
                        retorno = false;
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return retorno;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
