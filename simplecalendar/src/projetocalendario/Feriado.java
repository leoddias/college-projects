/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocalendario;

/**
 *
 * @author ACID
 */
public class Feriado extends Dia {
    private String nomeFeriado;
    
    Feriado(String nomeDia, String nome,int num)
    {
        super(nomeDia,num);
        nomeFeriado=nome;
    }

    public String getNomeFeriado() {
        return nomeFeriado;
    }

    public void setNomeFeriado(String nomeFeriado) {
        this.nomeFeriado = nomeFeriado;
    }
    
    
}
