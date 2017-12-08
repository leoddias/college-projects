/*#include <stdio.h>
#include <graphics.h>

  main()
  {
      initwindow(800,600,"Teste do Mouse", 200,200); // cria e abre uma janela gráfica com 400x300 pixels (largura x altura)
      moveto(0,0);         // move o cursor gráfico para a coordenada 0,0 (origem do plano cartesiano)

      readimagefile("Hospital.jpg", 0, 0, 800, 600);
    cleardevice();

      lineto(550,350);

      while(!kbhit());     // aguarda, em loop, até que uma tecla seja pressionada
      closegraph();        // fecha o modo gráfico e retorna ao modo de saída-padrão (texto)
  }
*/

#include <stdlib.h>

#include <stdio.h>

#include <conio.h>

#include <graphics.h>

int main(void)

{
    int fim=0,exame;



    initwindow (800,600,"Programando um Jogo",300,50);
    readimagefile("Hospital.jpg",0,0,800,600);

    do
    {
        exame=getch();
        printf("%d",exame);
        switch(exame)
        {
        case 49:
            outtextxy(20,50,"umm");
            fim=1;
            break;
        case 50:
            outtextxy(20,70,"doiss");
            fim = 1;
            break;
        default:
            outtextxy(50,200,"errooou");
            break;
        }
        printf("fim do swt");
    }while(fim!=1);

    closegraph();

    return 0;

}
