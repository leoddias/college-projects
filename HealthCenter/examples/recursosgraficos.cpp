/*+---------------------------------------------------------------------------------------------------------+
  | recursosgraficos.cpp                                                                                    |
  | Author: Prof. João Benedito dos Santos Junior, Ph.D.                                                    |
  | Date: May-21st, 2013                                                                                    |
  | From: PUC Minas, Brazil                                                                                 |
  | Objective: To develop basic graphic user interface with resources from graphics.h library               |
  +---------------------------------------------------------------------------------------------------------+*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <time.h>
#include <windows.h>
#include <graphics.h>


struct area {
    int x1, y1, x2, y2;
};
struct dado {
    char rotulo[30];
    float valor;
};
struct graph {
    char  titulo[60];
    area   dimensoes;
    dado   dados[100];
    int   tamanhoDaSerie;
};
void printMessageOnScreen(char message[], int x, int y, int background, int foreground, int border);
void drawButton(char label[], int x, int y);
void drawButtonPressed(char label[], int x, int y);
void drawButtonUnpressed(char label[], int x, int y);
int  getEndXCoordOfButton(char label[], int x);
void drawRadioButton(char message[], int x, int y, int background, int foreground);
void drawRadioButtonChecked(char message[], int x, int y, int background, int foreground, int checkedColor);
void drawRadioButtonUnchecked(char message[], int x, int y, int background, int foreground);
void barChart(struct graph g);

struct linesettingstype lineinfo;

main()
{
    char mensagem[100], rotulo[30];
    int  valor, sair = 0, marcou = 0;



    initwindow(800,600,"Home Network - Project", 200, 100, false, true);
    getlinesettings(&lineinfo);

    readimagefile("plantacasa.jpg", 10, 10, 286, 176);
    strcpy(mensagem, "Mensagem...");
    printMessageOnScreen(mensagem, 10,200,7,0,4);
    valor = 100;
    sprintf(mensagem, "Resultado = %d", valor);
    printMessageOnScreen(mensagem, 200,200,7,0,2);

    strcpy(rotulo, "Gerar Evento");
    drawButton(rotulo, 100, 260);
    strcpy(rotulo, "Sair");
    drawButton(rotulo, 100, 290);

    strcpy(rotulo, "Ligado");
    drawRadioButton(rotulo, 300, 260, 0, 14);

    // plota o gráfico de consumo mensal da residência.
    struct graph g;
    g.dimensoes.x1 = 350;
    g.dimensoes.y1 = 300;
    g.dimensoes.x2 = 790;
    g.dimensoes.y2 = 500;
    strcpy(g.titulo, "Consumo Mensal");

    g.tamanhoDaSerie = 12;
    strcpy(g.dados[0].rotulo, "Jan");
    strcpy(g.dados[1].rotulo, "Fev");
    strcpy(g.dados[2].rotulo, "Mar");
    strcpy(g.dados[3].rotulo, "Abr");
    strcpy(g.dados[4].rotulo, "Mai");
    strcpy(g.dados[5].rotulo, "Jun");
    strcpy(g.dados[6].rotulo, "Jul");
    strcpy(g.dados[7].rotulo, "Ago");
    strcpy(g.dados[8].rotulo, "Set");
    strcpy(g.dados[9].rotulo, "Out");
    strcpy(g.dados[10].rotulo, "Nov");
    strcpy(g.dados[11].rotulo, "Dez");

    srand(time(NULL));
    for (int i = 0; i < g.tamanhoDaSerie; i++) {
        g.dados[i].valor = (float) (rand() % 1000);
    }
    barChart(g);

    sair = 0;
    while(!sair) {
        if (mousex() >= 100 && mousex() <= getEndXCoordOfButton("Gerar Evento", 100) &&
                mousey() >= 260 && mousey() <= 285 &&
                ismouseclick(WM_LBUTTONDOWN)) {
            strcpy(rotulo, "Gerar Evento");
            drawButtonPressed(rotulo, 100, 260);
        }

        if (mousex() >= 290 && mousex() <= 310 &&
                mousey() >= 260 && mousey() <= 280 &&
                ismouseclick(WM_LBUTTONDOWN) && !marcou) {
            strcpy(rotulo, "Ligado");
            drawRadioButtonChecked(rotulo, 300, 260, 0, 14, 2);
            marcou = 1;
        }

        if (mousex() >= 290 && mousex() <= 310 &&
                mousey() >= 260 && mousey() <= 280 &&
                ismouseclick(WM_LBUTTONDOWN) && marcou) {
            strcpy(rotulo, "Ligado");
            drawRadioButtonUnchecked(rotulo, 300, 260, 0, 14);
            marcou = 0;
        }

        if (mousex() >= 100 && mousex() <= getEndXCoordOfButton("Sair", 100) &&
                mousey() >= 290 && mousey() <= 315 &&
                ismouseclick(WM_LBUTTONDOWN)) {
            strcpy(rotulo, "Sair");
            drawButtonPressed(rotulo, 100, 290);
            sair = 1;
        }
    };

    closegraph();
}

void printMessageOnScreen(char message[], int x, int y, int background, int foreground, int border)
{
    setfillstyle(1,background);
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);
    bar(x,y,x+textwidth(message)+12,y+25);
    setcolor(foreground);
    setbkcolor(background);
    outtextxy(x+6,y+2,message);
    setcolor(border);
    setlinestyle(0,1,2);
    rectangle(x,y,x+textwidth(message)+12,y+25);

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void drawButton(char label[], int x, int y)
{
    setfillstyle(1,7);
    setlinestyle(0,1,1);
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);

    bar(x,y,x+textwidth(label)+12,y+25);
    setcolor(0);
    setbkcolor(7);
    outtextxy(x+6,y+2,label);
    setcolor(15);
    line(x,y,x,y+25);
    line(x,y,x+textwidth(label)+12,y);
    setcolor(8);
    line(x+textwidth(label)+12,y,x+textwidth(label)+12,y+25);
    line(x+textwidth(label)+12,y+25,x,y+25);

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void drawButtonPressed(char label[], int x, int y)
{
    setlinestyle(0,1,1);

    while (ismouseclick(WM_LBUTTONDOWN)) {
        setcolor(8);
        line(x,y,x,y+25);
        line(x,y,x+textwidth(label)+12,y);
        setcolor(15);
        line(x+textwidth(label)+12,y,x+textwidth(label)+12,y+25);
        line(x+textwidth(label)+12,y+25,x,y+25);
        printf("\nAny button is pressed...");
        if (ismouseclick(WM_LBUTTONUP))
            break;
    }

    if (ismouseclick(WM_LBUTTONUP)) {
        drawButtonUnpressed(label, x, y);
        clearmouseclick(WM_LBUTTONDOWN);
        clearmouseclick(WM_LBUTTONUP);
    }

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void drawButtonUnpressed(char label[], int x, int y)
{
    setlinestyle(0,1,1);
    setcolor(15);
    line(x,y,x,y+25);
    line(x,y,x+textwidth(label)+12,y);
    setcolor(8);
    line(x+textwidth(label)+12,y,x+textwidth(label)+12,y+25);
    line(x+textwidth(label)+12,y+25,x,y+25);

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

int getEndXCoordOfButton(char label[], int x)
{
    return x+textwidth(label)+12;
}

void drawRadioButton(char message[], int x, int y, int background, int foreground)
{
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);
    setcolor(foreground);
    setbkcolor(background);
    outtextxy(x+14,y,message);
    setfillstyle(1,7);
    setlinestyle(0,1,2);
    setcolor(7);
    fillellipse(x,y+8,9,9);
    setcolor(8);
    circle(x,y+8,8);

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void drawRadioButtonChecked(char message[], int x, int y, int background, int foreground, int checkedColor)
{
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);
    setcolor(foreground);
    setbkcolor(background);
    outtextxy(x+14,y,message);
    setfillstyle(1,7);
    setlinestyle(0,1,2);
    setcolor(7);
    fillellipse(x,y+8,9,9);
    setcolor(8);
    circle(x,y+8,8);

    setfillstyle(1,checkedColor);
    setcolor(checkedColor);
    fillellipse(x,y+8,4,4);

    if (ismouseclick(WM_LBUTTONUP)) {
        clearmouseclick(WM_LBUTTONDOWN);
        clearmouseclick(WM_LBUTTONUP);
    }

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void drawRadioButtonUnchecked(char message[], int x, int y, int background, int foreground)
{
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);
    setcolor(foreground);
    setbkcolor(background);
    outtextxy(x+14,y,message);
    setfillstyle(1,7);
    setlinestyle(0,1,2);
    setcolor(7);
    fillellipse(x,y+8,9,9);
    setcolor(8);
    circle(x,y+8,8);

    if (ismouseclick(WM_LBUTTONUP)) {
        clearmouseclick(WM_LBUTTONDOWN);
        clearmouseclick(WM_LBUTTONUP);
    }

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void barChart(struct graph g)
{
    const int folga     = 5;
    const int escala    = 10;


    // Plota de forma centralizada o título do gráfico.
    outtextxy(g.dimensoes.x1+(g.dimensoes.x2-g.dimensoes.x1-textwidth(g.titulo))/2, g.dimensoes.y1, g.titulo);

    // Reduz o espaço vertical da área do gráfico em razão da exibição do título.
    g.dimensoes.y1 += textheight(g.titulo) + folga;

    // Obtem o maior valor do conjunto de valores. A versão atual desta função só trabalhará com
    // valores positivos.
    float maior = g.dados[0].valor;
    for (int i = 1; i < g.tamanhoDaSerie; i++) {
        if (g.dados[i].valor > maior) {
            maior = g.dados[i].valor;
        }
    }

    // converte o maior para o próximo maior número divisível pela escala.
    char mascara[8];
    maior = (((int)(maior/escala)) + 1)*escala;
    sprintf(mascara, "%s", maior);

    // plota a escala do eixo y.
    int altura  = ( g.dimensoes.y2 - g.dimensoes.y1 - folga );
    char rotulo[30];
    for (int i = 0, intervalo=maior/escala; i <= maior; i+=intervalo) {
        int posicao = ((i*altura)/maior);

        sprintf(rotulo, "%4d", i);
        outtextxy(g.dimensoes.x1, g.dimensoes.y2-posicao-folga-textheight(mascara)/2, rotulo);

        line(g.dimensoes.x1+textwidth(mascara), g.dimensoes.y2-posicao-folga,g.dimensoes.x1+textwidth(mascara)+folga,g.dimensoes.y2-posicao-folga);
    }
    // plota os eixos x e y.
    g.dimensoes.x1 = g.dimensoes.x1 + folga + textwidth(mascara);
    line(g.dimensoes.x1,g.dimensoes.y1,g.dimensoes.x1,g.dimensoes.y2);
    line(g.dimensoes.x1, g.dimensoes.y2-folga, g.dimensoes.x2, g.dimensoes.y2-folga);

    // plota as barras do gráfico.
    int largura = ( g.dimensoes.x2 - g.dimensoes.x1 - (g.tamanhoDaSerie) * folga ) / g.tamanhoDaSerie;
    int inicio = g.dimensoes.x1 + folga;
    int centro;

    for (int i=0; i< g.tamanhoDaSerie; i++) {
        // plota a barra com o valor normalizado.
        int normalizado = ((g.dados[i].valor*altura)/maior);
        bar(inicio, g.dimensoes.y2-folga, inicio+largura, (g.dimensoes.y2 - folga) - normalizado);

        // plota os rotulos do eixo x.
        centro = inicio+(largura/2);
        line(centro, g.dimensoes.y2-folga, centro, g.dimensoes.y2);
        outtextxy(inicio, g.dimensoes.y2, g.dados[i].rotulo);
        inicio = inicio + largura + folga;
    }
}
