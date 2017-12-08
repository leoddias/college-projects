#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <graphics.h>

main()
{
    int senha, guiche,continuar=0,atual,ante;
    char mensagemSenha[3], mensagemGuiche[2];
ante=initwindow(200,80,"Continuar ?", 400, 350, 0, 1);
atual = initwindow(200,80,"Continuar ?", 400, 350, 0, 1);
setcurrentwindow(atual);
    readimagefile("continuar.jpg",0,0,200,80);

 while(continuar!=2 || continuar!=1){
            printf("estou dentro do do\n");
            if (mousex() >= 12 && mousex() <= 85 &&
                    mousey() >= 40 && mousey() <= 67 &&
                    ismouseclick(WM_LBUTTONDOWN))
            {
                printf("clicou no nao\n");
                continuar=1;
                //break;
            }

            if (mousex() >= 113 && mousex() <= 183 &&
                    mousey() >= 40 && mousey() <= 67 &&
                    ismouseclick(WM_LBUTTONDOWN))
            {
                printf("clicou no nao\n");
                continuar=2;
               // break;
            }

        };
        closegraph(CURRENT_WINDOW);
        setcurrentwindow(ante);
    srand(time(NULL));

    while(!kbhit())
    {
       printf("\nX=%d, Y=%d ", mousex(), mousey());
       Sleep(1000);
       senha = rand() % 100;
       guiche = rand() % 10;

       sprintf(mensagemSenha, "%d", senha);
       outtextxy(70,60,mensagemSenha);
       sprintf(mensagemGuiche, "%d", guiche);
       outtextxy(70,120,mensagemGuiche);
    }

    closegraph(); // fecha o modo gráfico e retorna ao modo de saída-padrão (texto)
}
