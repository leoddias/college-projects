#include <stdio.h>
#include <graphics.h>
  main()
  {
      initwindow(800,600,"Teste do Mouse", 300,50); // cria e abre uma janela gr�fica com 400x300 pixels (largura x altura)
      readimagefile("Cardiologista.jpg",0,0, 800, 600);

      // aguarda, em loop, at� que uma tecla seja pressionada
      while(!kbhit())
      {
         printf("\nX=%d, Y=%d ", mousex(), mousey());
        outtextxy(540,480,"Sua Op��o: ");

         if (mousex() > 10 && mousex() < 50 &&
             mousey() > 10 && mousey() < 50 &&
             ismouseclick(WM_LBUTTONDOWN))
             printf("\nHello, world!!!");

        clearmouseclick(WM_LBUTTONDOWN);
      };

      closegraph(); // fecha o modo gr�fico e retorna ao modo de sa�da-padr�o (texto)
  }
