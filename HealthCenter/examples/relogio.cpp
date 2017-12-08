#include <stdio.h>
#include <graphics.h>
  main()
  {
      initwindow(290,174,"Teste do Mouse",200,200); // cria e abre uma janela gráfica com 290x174 pixels (largura x altura)
      readimagefile("relogio1.jpg",0,0,290,174);

      // aguarda, em loop, até que uma tecla seja pressionada
      setcolor(0);
      rectangle(234,127,286,156);
      setfillstyle(1,7);
      bar(235,128,285,155);
      setfillstyle(1,7);
      setbkcolor(7);
      setcolor(0);
      outtextxy(250,135,"OK");

      while(!kbhit())
      {
         printf("\nX=%d, Y=%d ", mousex(), mousey());
         Sleep(500);

         if (mousex() >= 98 && mousex() < 115 &&
             mousey() >= 131 && mousey() < 155 &&
             ismouseclick(WM_LBUTTONDOWN))
         {
             printf("\nPrimeiro Digito do Relogio!!!");
             setfillstyle(1,15);
             setbkcolor(15);
             setcolor(4);
             outtextxy(103,135,"1");
         }

         if (mousex() >= 235 && mousex() < 285 &&
             mousey() >= 128 && mousey() < 155 &&
             ismouseclick(WM_LBUTTONDOWN))
         {
             printf("\nClickou no OK...");

             setfillstyle(1,15);
             setbkcolor(15);
             setcolor(4);
             outtextxy(103,135,"0");

             setfillstyle(1,15);
             setbkcolor(15);
             setcolor(4);
             outtextxy(122,135,"0");
         }

         if (mousex() >= 117 && mousex() < 131 &&
             mousey() >= 131 && mousey() < 155 &&
             ismouseclick(WM_LBUTTONDOWN))
         {
             printf("\nSegundo Digito do Relogio!!!");
             setfillstyle(1,15);
             setbkcolor(15);
             setcolor(4);
             outtextxy(122,135,"2");
         }


        clearmouseclick(WM_LBUTTONDOWN);
      };

      closegraph(); // fecha o modo gráfico e retorna ao modo de saída-padrão (texto)
  }
