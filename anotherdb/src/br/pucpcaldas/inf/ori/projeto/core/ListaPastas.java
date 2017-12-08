package br.pucpcaldas.inf.ori.projeto.core;

import java.io.File;
import java.util.ArrayList;

public class ListaPastas {
        File stream;
	String nomes[];
        String caminho;
        public ArrayList<String> listaDB = new ArrayList();
	int i;
    public ListaPastas(String identificador)
    {
        stream = new File(identificador);	
		if (stream.exists())
		{
			if (stream.isDirectory())
			{
				nomes = stream.list();
				for (i=0; i < nomes.length; i++)	
				{
                                        caminho = new String(identificador+"\\"+nomes[i]);
					stream = new File(caminho);
					if (stream.isDirectory())
						listaDB.add(nomes[i]);
				}				    
			}
		}
    }
    public ArrayList<String> getLista(){
        return listaDB;
    }
    
}


