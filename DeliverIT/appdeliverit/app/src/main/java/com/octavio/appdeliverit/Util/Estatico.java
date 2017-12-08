package com.octavio.appdeliverit.Util;

import com.octavio.appdeliverit.Model.Produto;

import java.util.ArrayList;

/**
 * Created by Octavio Sabatine Ranieri on 27/03/2016.
 */
public class Estatico {

    public Estatico(){

    }
    //TODO: Fazer metodo getLocal
    public static String getLocal(){
        return "10.4848451,25.585485445";
    }

    public static final String ipServidor = "192.168.0.102";
    public static ArrayList<Produto> carrinho = new ArrayList<>();
}
