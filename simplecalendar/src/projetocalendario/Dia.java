package projetocalendario;

public class Dia {
    String nomeDia;
    int numDia;
    
    Dia(String nome,int num)
    {
        numDia=num;
        nomeDia = nome;
    }

    public String getNomeDia() {
        return nomeDia;
    }

    public void setNomeDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }

    public int getNumDia() {
        return numDia;
    }

    public void setNumDia(int numDia) {
        this.numDia = numDia;
    }
    
  
}
