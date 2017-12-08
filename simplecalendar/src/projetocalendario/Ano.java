package projetocalendario;

public class Ano {
    private int numAno;
    private boolean bisexto=false;
    Mes[] mesAno;
    
    Ano(int num)
    {
        numAno=num;
        mesAno = new Mes[12];
        mesAno[0] = new Mes(1,31,"Janeiro",numAno);
        mesAno[1] = new Mes(2,28,"Fevereiro",numAno);
        mesAno[2] = new Mes(3,30,"Março",numAno);
        mesAno[3] = new Mes(4,31,"Abril",numAno); 
        mesAno[4] = new Mes(5,30,"Maio",numAno);    
        mesAno[5] = new Mes(6,31,"Junho",numAno); 
        mesAno[6] = new Mes(7,30,"Julho",numAno); 
        mesAno[7] = new Mes(8,31,"Agosto",numAno); 
        mesAno[8] = new Mes(9,30,"Setembro",numAno); 
        mesAno[9] = new Mes(10,31,"Outubro",numAno);
        mesAno[10] = new Mes(11,30,"Novembro",numAno); 
        mesAno[11] = new Mes(12,31,"Dezembro",numAno);
      
        if ( ( numAno % 4 == 0 && numAno % 100 != 0 ) || numAno % 400 == 0 )
        {
            bisexto=true;
            mesAno[1] = new Mes(2,29,"Fevereiro",numAno);
        }
    }

    public int getNumAno() {
        return numAno;
    }

    public void setNumAno(int numAno) {
        this.numAno = numAno;
    }

    public boolean isBisexto() {
        return bisexto;
    }

    public void setBisexto(boolean bisexto) {
        this.bisexto = bisexto;
    }

    public Mes[] getMesAno() {
        return mesAno;
    }

    public void setMesAno(Mes[] mesAno) {
        this.mesAno = mesAno;
    }
    
    
    
    
}
