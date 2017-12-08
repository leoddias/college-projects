/*+----------------------------------------------------------------------------+
  | Mouse.c                                                                    |
  | Objetivo: habilitar e permitir o uso de mouse num modo grafico padrao      |
  |           emulado em ambiente DOS, a partir dos recursos graphics.h        |
  | Autor: Joao Benedito dos Santos Junior, Ph.D.                              |
  | Original: junho de 1990 - Remake: outubro de 2006                          |
  +----------------------------------------------------------------------------+*/

// Apresenta rotinas de mouse aliadas ao modo gráfico
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <dos.h>
#include <graphics.h>

unsigned int IniciaMouse (void);
int CoordX (void);
int CoordY (void);
void IndicadorMouse (void);
void SemIndicadorMouse (void);
int BotaoCentral (void);
int BotaoDireita (void);
int BotaoEsquerda (void);

void main (void)
{
 int DriverGrafico, ModoGrafico, Erro;
 int X,Y;
 unsigned int MouseInstalado, Finaliza;
 int BotaoEsq, BotaoDir;

 DriverGrafico = DETECT;
 initgraph (&DriverGrafico,&ModoGrafico,"c:\\tcpp\\bgi");
 Erro = graphresult( );
 
 if (Erro != grOk)
 {
  printf ("Erro de Abertura do Modo Grafico");
  exit (1);
 }

 MouseInstalado = IniciaMouse( );
 if (!MouseInstalado)
    exit(1);

 setfillstyle(1,15); setcolor(15);
 rectangle(0,1,100,51);
 rectangle(0,60,100,110);
 rectangle(0,120,100,170);
 setfillstyle(1,11); setcolor(11);
 outtextxy(20,22,"Mensagem");
 outtextxy(20,82," Avisos ");
 outtextxy(20,142,"Finaliza");
 IndicadorMouse( );
 Finaliza = 0;
 do
 {
  BotaoEsq = 0;
  BotaoDir = 0;
  BotaoEsq = BotaoEsquerda( );
   BotaoDir = BotaoDireita( );

   if ((CoordX( ) >= 0) && (CoordX( ) <= 100) &&
      (CoordY( ) >= 1) && (CoordY( ) <= 51) &&
      (BotaoEsq != 0))
  {
   setfillstyle(1,7); setcolor(7);
   SemIndicadorMouse( );
   bar(200,80,520,100);
   setfillstyle(1,0); setcolor(0);
   outtextxy(215,88,"Bem vindo ao mundo grafico...");
   IndicadorMouse( );
  }

 if ((CoordX( ) >= 0) && (CoordX( ) <= 100) &&
      (CoordY( ) >= 60) && (CoordY( ) <= 110) &&
      (BotaoEsq != 0))
  {
   setfillstyle(1,7); setcolor(7);
   SemIndicadorMouse( );
   bar(200,80,520,100);
   setfillstyle(1,4); setcolor(4);
   outtextxy(210,88,"Programe sempre com cautela e atencao");
   IndicadorMouse( );
  }

  if ((CoordX() >= 0) && (CoordX() <= 100) &&
      (CoordY() >= 120) && (CoordY() <= 170) &&
      (BotaoEsq != 0))
      Finaliza = 1;
 } while ((!Finaliza) && (BotaoDir == 0));
 
 IndicadorMouse( );
 closegraph ( );
}



unsigned int IniciaMouse (void)
{
 struct REGPACK Registradores;
 Registradores.r_ax = 0;
 intr (0x33, &Registradores);
 return Registradores.r_ax;
}

int CoordX (void)
{
 struct REGPACK Registradores;
 Registradores.r_ax = 03;
 intr (0x33, &Registradores);
 return Registradores.r_cx;
}

int CoordY (void)
{
 struct REGPACK Registradores;
 Registradores.r_ax = 03;
 intr (0x33, &Registradores);
 return Registradores.r_dx;
}

void IndicadorMouse (void)
{
 union REGS Registradores;
 Registradores.x.ax = 0x01;  Registradores.x.bx = 2;
 int86(0x33,&Registradores,&Registradores);
 }

void SemIndicadorMouse (void)
{
 union REGS Registradores;
 Registradores.x.ax = 0x02;
 int86 (0x33,&Registradores,&Registradores);
}

int BotaoCentral (void)
{
 union REGS Registradores;
 Registradores.x.ax = 0x05;
 Registradores.x.bx = 2;
 int86(0x33,&Registradores,&Registradores);
 return Registradores.x.bx;
}

int BotaoDireita (void)
{
 union REGS Registradores;
 Registradores.x.ax = 0x05;
 Registradores.x.bx = 1;
 int86(0x33,&Registradores,&Registradores);
 return Registradores.x.bx;
}

int BotaoEsquerda (void)
{
 union REGS Registradores;
 Registradores.x.ax = 0x05;
 Registradores.x.bx = 0;
 int86(0x33,&Registradores,&Registradores);
 return Registradores.x.bx;
}


