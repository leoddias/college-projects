/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.ori.projeto.core;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author OCTAVIO-NB
 */
public class ListaTabelas {
    File stream;
	String nomes[];
        String caminho;
        public ArrayList<String> listaTabelas = new ArrayList();
	int i;
    public ListaTabelas(String identificador)
    {
        stream = new File(identificador);	
		if (stream.exists())
		{
			if (stream.isDirectory())
			{
				nomes = stream.list();
				for (i=0; i < nomes.length; i++)	
				{       
                                        if (nomes[i].substring(nomes[i].length()-6, nomes[i].length()).equals(".table")){
                                        caminho = new String(identificador+"\\"+nomes[i]);
					stream = new File(caminho);
					if (stream.isFile())
						listaTabelas.add(nomes[i].substring(0, nomes[i].length()-6));
                                        }
				}				    
			}
		}
    }
    public ArrayList<String> getLista(){
        return listaTabelas;
    }
}
