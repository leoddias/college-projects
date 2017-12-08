package projetocalendario;

public class Mes {
    Dia[] diaMes;
    private int qtdDia;
    private String nomeMes;
    private int numMes;
    
    Mes(int num, int qtd, String nome, int numA)
    {
        numMes = num;
        qtdDia=qtd;
        nomeMes=nome;
        diaMes = new Dia[qtdDia];
        
        for (int i=0; i < qtdDia; i++)
        {
          diaMes[i] = new DiaUtil(findNome(numA, numMes, i+1),i);
        }
        
        if(numMes == 1)
        {
                 diaMes[0] = new Feriado(findNome(numA, numMes, 1),"Ano novo",1);
        }
        if(numMes==2)
        {
            diaMes[15] = new Feriado(findNome(numA, numMes, 16),"Carnaval",16);  
            diaMes[16] = new Feriado(findNome(numA, numMes, 17),"Carnaval",17);  
            diaMes[17] = new Feriado(findNome(numA, numMes, 18),"Quarta-Feira de Cinzas",18);          
        }
        if(numMes==4)
        {
            diaMes[2] = new Feriado(findNome(numA, numMes, 3),"Sexta Feira da Paixao",3); 
            diaMes[20] = new Feriado(findNome(numA, numMes, 21),"Tiradentes",21);
        }
        if(numMes==5)
           diaMes[0] = new Feriado(findNome(numA, numMes, 1),"Dia do Trabalho",1);
        
        
        if(numMes==6)
            diaMes[3] = new Feriado(findNome(numA, numMes, 4),"Corpus Christi",4); 
            
        
        if(numMes==9)      
            diaMes[6] = new Feriado(findNome(numA, numMes, 7),"Independecia do Brasil",7); 
           
        
        if(numMes==10)
        {
            diaMes[11] = new Feriado(findNome(numA, numMes, 12),"Nossa Senhora",12);
            diaMes[29] = new Feriado(findNome(numA, numMes, 30),"Dia do Servidor Publico",30);
        }
        if(numMes==11)
        {
            diaMes[1] = new Feriado(findNome(numA, numMes, 2),"Finados",2);
            diaMes[14] = new Feriado(findNome(numA, numMes, 15),"Proclamação da República",15);
        }
        
        if(numMes==12)
        {
            diaMes[23] = new Feriado(findNome(numA, numMes, 24),"Véspera de Natal",24);
            diaMes[24] = new Feriado(findNome(numA, numMes, 25),"Natal",25);
            diaMes[29] = new Feriado(findNome(numA, numMes, 31),"Véspera de Ano Novo",31);
        }       
            
    }
       public String findNome(int year,int month, int day)
    {

        int anchor = 0; //Holds the name of the anchor day as a number, 0 to 6.
        int runningTotal=0; //The changing values of the "Odd + 11" algorithm.
        int doomsday=0; //The recurring name of day on known dates for a year.
        int leapYear=0; //The leap year status, true or false.
        int doomsdayDayDate = 0; //The calendar number of the Doomsday day.
        int dayName = 0; //Name of the given day as a number, 0 to 6.

        if ((year - (year % 100)) % 400 == 0) {
            anchor = 2;
        } else if ((year - (year % 100)) % 400 == 100) {
            anchor = 0;
        } else if ((year - (year % 100)) % 400 == 200) {
            anchor = 5;
        } else if ((year - (year % 100)) % 400 == 300) {
            anchor = 3;
        }

        runningTotal = (year % 100);
        if ((runningTotal % 2) != 0) {
            runningTotal = (runningTotal + 11);
        }
        runningTotal = (runningTotal / 2);
        if ((runningTotal % 2) != 0) {
            runningTotal = (runningTotal + 11);
        }
        runningTotal = (7 - (runningTotal % 7));
        doomsday = ((anchor + runningTotal) % 7);

        if ((year % 400) == 0) {
            leapYear = 1;
        } else if((year % 100) == 0) {
            leapYear = 0;
        } else if((year % 4) == 0) {
            leapYear = 1;
        } else {
            leapYear = 0;
        }


        if (leapYear == 1 && month == 1) {
            doomsdayDayDate = 4;
        } else if (leapYear == 0 && month == 1) {
            doomsdayDayDate = 3;
        } else if (leapYear == 1 && month == 2) {
            doomsdayDayDate = 29;
        } else if (leapYear == 0 && month ==2) {
            doomsdayDayDate = 28;
        } else if (month == 3) {
            doomsdayDayDate = 0;
        } else if (month == 4) {
            doomsdayDayDate = 4;
        } else if (month == 5) {
            doomsdayDayDate = 9;
        } else if (month == 6) {
            doomsdayDayDate = 6;
        } else if (month == 7) {
            doomsdayDayDate = 11;
        } else if (month == 8) {
            doomsdayDayDate = 8;
        } else if (month == 9) {
            doomsdayDayDate = 5;
        } else if (month == 10) {
            doomsdayDayDate = 10;
        } else if (month == 11) {
            doomsdayDayDate = 7;
        } else if (month == 12) {
            doomsdayDayDate = 12;
        }       

        if (day >= doomsdayDayDate) {
            dayName=((doomsday+((day-doomsdayDayDate)%7))%7);
        } else if (day < doomsdayDayDate) {
            dayName=((doomsday+(7-((doomsdayDayDate-day)%7)))%7);
        }  
        
        if (dayName==0)
            return("Domingo");
        if (dayName==1)
           return("Segunda");
        if (dayName==2)
          return("Terça");
        if (dayName==3)
          return("Quarta");
        if (dayName==4)
          return("Quinta");
        if (dayName==5)
          return("Sexta");
        if (dayName==6)
          return("Sabado");
 
        return("Dia");
    }

    public Dia[] getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(Dia[] diaMes) {
        this.diaMes = diaMes;
    }

    public int getQtdDia() {
        return qtdDia;
    }

    public void setQtdDia(int qtdDia) {
        this.qtdDia = qtdDia;
    }

    public String getNomeMes() {
        return nomeMes;
    }

    public void setNomeMes(String nomeMes) {
        this.nomeMes = nomeMes;
    }

    public int getNumMes() {
        return numMes;
    }

    public void setNumMes(int numMes) {
        this.numMes = numMes;
    }
       
}
