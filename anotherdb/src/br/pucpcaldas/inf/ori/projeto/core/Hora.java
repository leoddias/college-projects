/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.ori.projeto.core;

import java.util.GregorianCalendar;

/**
 *
 * @author OCTAVIO-NB
 */
public class Hora {

    
    public Hora() {
    }
    
    public String getHora()
    {
        StringBuilder sb = new StringBuilder();
        GregorianCalendar d = new GregorianCalendar();

        sb.append(d.get(GregorianCalendar.HOUR_OF_DAY));
        sb.append(":");
        sb.append(d.get(GregorianCalendar.MINUTE));
        sb.append(":");
        sb.append(d.get(GregorianCalendar.SECOND));

        return sb.toString();
    }
    
}
