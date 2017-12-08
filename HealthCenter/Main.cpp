/*
    Nome do projeto:    HealthCenter();
    Objetivo: Gerenciar filas de um Centro de Saúde

    Alunos:     Leonardo Daniel Dias
                Marcelo Rezende de Assis

    Projeto da disciplina de Estruturas de Dados.
    Prof. Dr. João Benedito dos Santos Junior
    PUC Minas - Campus de Poços de Caldas
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <windows.h>
#include <math.h>
#include <graphics.h>
#define ESPERAR 700
#define BUFFANIMA 0
#define COR 7
#define BGCOR 0
#define BUFF 150

//////////////////////////
const int dX1 = 30;     ////
const int dX2 = 760;    ////// Define as Dimensões do barchart
const int dY1 = 80;     ////
const int dY2 = 540;    //
/////////////////////////

typedef struct
{
    int tipoFila;   // 0 = Normal, 1 = Prioritario, 2 = Emergencial
    int tipoMedico; // 0 (C) = Cardiologista, 1 (P) = Pediatra, 2 (O) = Oftalmologista, 3 (N) = Neurologista, 4 (D) = Dermatologista
    int numSenha;   // Número da Senha de acordo com o tipo da fila
} Senha;

typedef struct
{
    Senha senhaPaciente;  // Senha gerada para o Paciente
    time_t horaChegada;   // Horario de Chegada do Paciente ao Health Center
    time_t horaSaida;     // Horario que o paciente deixou o consultorio]
    int exame;            // Qual exame escolhido 0 1 ou 2
} Pacientes;

struct noPacientes
{
    Pacientes dadoPaciente;
    struct noPacientes *proximo;
};

typedef struct
{
    struct noPacientes *inicioNoPacientes;

} filaPaciente;

//////////////////////////////////
struct area                     //
{                               //
                                //
    int x1, y1, x2, y2;         //
};                              //
struct dado                     //
{                               //
                                //
    char rotulo[30];            ////////
    float valor;                /////////// Estrutura utilizara para o barChart();
};                              ////////Desenvolvido por: Prof. Luiz Alberto
struct graph                    //
{                               //
                                //
    char  titulo[60];           //
    area   dimensoes;           //
    dado   dados[100];          //
    int   tamanhoDaSerie;       //
};                              //
void barChart(struct graph g);  //
//////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
void drawRadioButton(char message[], int x, int y, int background, int foreground);                             //
void drawRadioButtonChecked(char message[], int x, int y, int background, int foreground, int checkedColor);    /////
void drawRadioButtonUnchecked(char message[], int x, int y, int background, int foreground);                    //////// Funções para o uso do radiobutton
struct linesettingstype lineinfo;                                                                               ///// Desenvolvido por: Dr. João Benedito
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////
time_t getTimeSystem();                                                                     //// Recebe tempo do Sistema Author: Paulo Muniz de Ávila
double contarTempoMedico(filaPaciente f,int exame,int medico);                              //
double contarTempoFila(filaPaciente f,int fila);                                            //
int push(filaPaciente *f, Pacientes p);                                                     // Inserir na Fila
int contaFila(filaPaciente f,int fila);                                                     // Contar numero de nos em uma fila 0,1,2
int contaMedico(filaPaciente f, int medico);                                                // Conta numero de nós de pacientes atendido por X medico
int contaExame(filaPaciente f, int exame, int medico);                                      // Conta quantos exames feitos de X medico
void create(filaPaciente *f);                                                               // Criar uma Fila
void printaSenha(Pacientes p);                                                              // Printa a senha do paciente no mini-monitor do cenario
void criarMenu();                                                                           // Desenha o menu de senhas GRAPICHS
void initMenu(filaPaciente *fila,int telaPrincipal, int telaSenha, int telaDescricao);      // Inicializa o Menu Gerador de Senhas
void iniciarSimulacao(filaPaciente *fila,int menuF,int menuM,int exame,int aleatorio,int telaDescricao,int telaPrincipal);      // Inicializa a Simulação recebendo parametros do initMenu
void plotaDescricao(int medico, int exame);                                                 // Plota na tela a descricao do medico e exame selecionado ou gerado aleatoriamente
void criaMenuRelatorios();                                                                  // Desenha os botões no menu de relatorios na grapichs
void gerarRelatorios(filaPaciente fila,double tempoExecucao);                               // Função que gera relatorios e gerencia o menu de relatorios até o fim da execução.
void relatorioSoftware(filaPaciente f,double tempoExecucao);                                // Função para gerar relatorio do software
void andaPaciente(int medico);                                                              // Função para fazer animacao do paciente ate o medico
//////////////////////////////////////////////////////////////////////////////////////////////


int main()
{
    filaPaciente fila;
    int telaPrincipal;
    int telaSenha;
    int telaDescricao;
    time_t inicioSoftware,fimSoftware;

    srand(time(NULL));          // Gerador de Seed
    create(&fila);              // Cria uma Fila setando os ponteiros para NULL

    telaPrincipal=initwindow(800,600,"HealthCenter(Principal);", 0, 0, 0, 0); // Cria a tela Principal
    readimagefile("welcome.jpg", 0, 0, 800, 600);                             // Lê imagem de Boas Vindas
    inicioSoftware = getTimeSystem();
    getch();                                                                  // Aguarda uma tecla qualquer
    cleardevice();                                                            //
    readimagefile("cenario.jpg",0,0,800,600);                                 // Lê imagem do Cenário aguardando uma senha....
    telaDescricao=initwindow(540,215,"HealthCenter(Descrição)", 810, 500, 0, 0); // Cria a tela de Descrição
    plotaDescricao(0,0);                                                       //
    telaSenha=initwindow(300,465,"HealthCenter(GerarSenha)", 810, 0, 0, 0);      // Cria a tela de Gerador de Senhas

    setcurrentwindow(telaSenha); // Define a tela de Senha como Principal
    initMenu(&fila,telaPrincipal,telaSenha,telaDescricao);  // Inicializa a função para gerar a senha

    closegraph();
    Sleep(ESPERAR);

    initwindow(1360,695,"HealthCenter(Relatorios);", 0, 0, 0, 0);
    getlinesettings(&lineinfo);     // Função para linhas da função barChart();
    fimSoftware = getTimeSystem();  //
    gerarRelatorios(fila,difftime(fimSoftware,inicioSoftware));          // Função para o Menu de Relatorios
    closegraph();                   // Fim da execução, fecha todas janelas.

}
void andaPaciente(int medico)
{
    int i;

    for(i=0; i<300; i+=2)
    {
        readimagefile("pacienteFrente.jpg",290,450-i, 290+24, 450+24-i);
        Sleep(BUFFANIMA);
    }

    if(medico==0)
    {
        readimagefile("pacienteEsquerda.jpg",290,150, 290+24, 150+24);
        for(i=0; i<130; i+=2)
        {
            readimagefile("pacienteEsquerda.jpg",290-i,150, 290+24-i, 150+24);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteFrente.jpg",160,150, 160+24, 150+24);
        for(i=0; i<75; i+=2)
        {
            readimagefile("pacienteFrente.jpg",160,150-i, 160+24, 150+24-i);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteEsquerda.jpg",160,75, 160+24, 75+24);
         for(i=0; i<100; i+=2)
         {
             readimagefile("pacienteEsquerda.jpg",160-i,75, 160+24-i, 75+24);
             Sleep(BUFFANIMA);
         }
    }

    if(medico==1)
    {
        for(i=0; i<70; i+=2)
        {
            readimagefile("pacienteFrente.jpg",290,150-i, 290+24, 150+24-i);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteDireita.jpg",290,80, 290+24, 80+24);
        for(i=0;i<25;i+=2)
        {
            readimagefile("pacienteDireita.jpg",290+i,80, 290+24+i, 80+24);
            Sleep(BUFFANIMA);
        }
    }

    if(medico==2)
    {
        readimagefile("pacienteDireita.jpg",290,150, 290+24, 150+24);
        for(i=0;i<120;i+=2)
        {
            readimagefile("pacienteDireita.jpg",290+i,150, 290+24+i, 150+24);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteFrente.jpg",410,150, 410+24, 150+24);
        for(i=0;i<70;i+=2)
        {
            readimagefile("pacienteFrente.jpg",410,150-i, 410+24, 150+24-i);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteDireita.jpg",410,80, 410+24, 80+24);
         for(i=0;i<100;i+=2)
         {
             readimagefile("pacienteDireita.jpg",410+i,80, 410+24+i, 80+24);
             Sleep(BUFFANIMA);
         }
    }

    if(medico==3)
    {
        readimagefile("pacienteDireita.jpg",290,150, 290+24, 150+24);
        for(i=0;i<320;i+=2)
        {
            readimagefile("pacienteDireita.jpg",290+i,150, 290+24+i, 150+24);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteFrente.jpg",610,150, 610+24, 150+24);
        for(i=0;i<70;i+=2)
        {
            readimagefile("pacienteFrente.jpg",610,150-i, 610+24, 150+24-i);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteDireita.jpg",610,80, 610+24, 80+24);
        for(i=0;i<110;i+=2)
        {
            readimagefile("pacienteDireita.jpg",610+i,80, 610+24+i, 80+24);
            Sleep(BUFFANIMA);
        }
    }

    if(medico==4)
    {
         readimagefile("pacienteDireita.jpg",290,150, 290+24, 150+24);
        for(i=0;i<400;i+=2)
        {
            readimagefile("pacienteDireita.jpg",290+i,150, 290+24+i, 150+24);
            Sleep(BUFFANIMA);
        }
        for(i=0;i<50;i+=2)
        {
            readimagefile("pacienteBaixo.jpg",690,150+i, 690+24, 150+24+i);
            Sleep(BUFFANIMA);
        }
        readimagefile("pacienteDireita.jpg",690,200, 690+24, 200+24);
        for(i=0;i<10;i+=2)
        {
            readimagefile("pacienteDireita.jpg",690+i,200, 690+24+i, 200+24);
            Sleep(BUFFANIMA);
        }
    }
}

void plotaDescricao(int medico, int exame)
{

    if(medico==1)
    {
        readimagefile("descricaoCardiologista.jpg",0,0,540,60);
        if(exame==0)
            readimagefile("descricaoAguardaExame.jpg",0,60,540,215);
        if(exame==1)
            readimagefile("descricaoCardiologista1.jpg",0,60,540,215);
        if(exame==2)
            readimagefile("descricaoCardiologista2.jpg",0,60,540,215);
        if(exame==3)
            readimagefile("descricaoCardiologista3.jpg",0,60,540,215);
    }

   if(medico==2)
    {
        readimagefile("descricaoPediatra.jpg",0,0,540,60);
        if(exame==0)
            readimagefile("descricaoAguardaExame.jpg",0,60,540,215);
        if(exame==1)
            readimagefile("descricaoPediatra1.jpg",0,60,540,215);
        if(exame==2)
            readimagefile("descricaoPediatra2.jpg",0,60,540,215);
        if(exame==3)
            readimagefile("descricaoPediatra3.jpg",0,60,540,215);
    }

     if(medico==3)
    {
        readimagefile("descricaoOftalmologista.jpg",0,0,540,60);
        if(exame==0)
            readimagefile("descricaoAguardaExame.jpg",0,60,540,215);
        if(exame==1)
            readimagefile("descricaoOftalmologista1.jpg",0,60,540,215);
        if(exame==2)
            readimagefile("descricaoOftalmologista2.jpg",0,60,540,215);
        if(exame==3)
            readimagefile("descricaoOftalmologista3.jpg",0,60,540,215);
    }

     if(medico==4)
    {
        readimagefile("descricaoNeurologista.jpg",0,0,540,60);
        if(exame==0)
            readimagefile("descricaoAguardaExame.jpg",0,60,540,215);
        if(exame==1)
            readimagefile("descricaoNeurologista1.jpg",0,60,540,215);
        if(exame==2)
            readimagefile("descricaoNeurologista2.jpg",0,60,540,215);
        if(exame==3)
            readimagefile("descricaoNeurologista3.jpg",0,60,540,215);
    }

     if(medico==5)
    {
        readimagefile("descricaoDermatologista.jpg",0,0,540,60);
        if(exame==0)
            readimagefile("descricaoAguardaExame.jpg",0,60,540,215);
        if(exame==1)
            readimagefile("descricaoDermatologista1.jpg",0,60,540,215);
        if(exame==2)
            readimagefile("descricaoDermatologista2.jpg",0,60,540,215);
        if(exame==3)
            readimagefile("descricaoDermatologista3.jpg",0,60,540,215);
    }

    if(medico==0)
        readimagefile("descricaoAguardando.jpg",0,0,540,215);
    return;
}


void relatorioSoftware(filaPaciente f,double tempoExecucao)
{
    char escrever[4]{0,0,0,0};
    struct noPacientes *aux;
    int c=0;
    aux = f.inicioNoPacientes;
    setbkcolor(WHITE);
    setcolor(BLACK);
    settextstyle(10,HORIZ_DIR,1);
    while(aux!=NULL)
    {
        c++;
        aux=aux->proximo;
    }

    outtextxy(50,200,"Tempo total de execução do software:                              segundos");
    itoa(tempoExecucao,escrever,10);// Converte INT para String
    outtextxy(443,200,escrever);

    outtextxy(50,220,"Quantidade de Pacientes atendidos:                                 pacientes");
    itoa(c,escrever,10);// Converte INT para String
    outtextxy(425,220,escrever);

    outtextxy(50,240,"Quantidade de Pacientes na Fila Normal:                         pacientes");
    itoa(contaFila(f,0),escrever,10);// Converte INT para String
    outtextxy(50*9.5,240,escrever);

    outtextxy(50,260,"Quantidade de Pacientes na Fila Prioritaria:                     pacientes");
    itoa(contaFila(f,1),escrever,10);// Converte INT para String
    outtextxy(500,260,escrever);

    outtextxy(50,280,"Quantidade de Pacientes na Fila Emergencial:                pacientes");
    itoa(contaFila(f,2),escrever,10);// Converte INT para String
    outtextxy(525,280,escrever);

    outtextxy(50,300,"Pacientes atendidos por Cardiologista:                            pacientes");
    itoa(contaMedico(f,0),escrever,10);// Converte INT para String
    outtextxy(50*9,300,escrever);

    outtextxy(50,320,"Pacientes atendidos por Pediatra:                                     pacientes");
    itoa(contaMedico(f,1),escrever,10);// Converte INT para String
    outtextxy(400,320,escrever);

    outtextxy(50,340,"Pacientes atendidos por Oftalmologista:                          pacientes");
    itoa(contaMedico(f,2),escrever,10);// Converte INT para String
    outtextxy(465,340,escrever);

    outtextxy(50,360,"Pacientes atendidos por Neurologista:                             pacientes");
    itoa(contaMedico(f,3),escrever,10);// Converte INT para String
    outtextxy(445,360,escrever);

    outtextxy(50,380,"Pacientes atendidos por Dermatologista:                         pacientes");
    itoa(contaMedico(f,4),escrever,10);// Converte INT para String
    outtextxy(470,380,escrever);

}
void criaMenuRelatorios()
{
    readimagefile("relatorioAtendimento.jpg", 45, 195, 45+176, 195+52);
    readimagefile("relatorioMedicos.jpg", 45, 250, 45+176, 250+52);
    readimagefile("relatorioSoftware.jpg", 45, 305, 45+176, 305+52);
    readimagefile("relatorioCardiologista.jpg", 450, 195, 450+176, 195+52);
    readimagefile("relatorioPediatra.jpg", 450, 250, 450+176, 250+52);
    readimagefile("relatorioOftalmologista.jpg", 450, 305, 450+176, 305+52);
    readimagefile("relatorioNeurologista.jpg", 450, 360, 450+176, 360+52);
    readimagefile("relatorioDermatologista.jpg", 450, 415, 450+176, 415+52);
    readimagefile("finalizar.jpg", 1184, 643, 1184+176, 643+52);
}

void gerarRelatorios(filaPaciente fila,double tempoExecucao)
{
    int fim=0;
    char* titulo[60];
    int voltar=0;
    struct graph porAtendimento,porMedico,porExameCardiologista,porExamePediatra,
            porExameOftalmologista,porExameNeurologista,porExameDermatologista,
            porTempoAtendimento,porTempoSoftware,porTempoMedico,porTempoCardiologista,
            porTempoPediatra,porTempoOftalmologista,porTempoNeurologista,
            porTempoDermatologista;

//////////////////////////////////////////////////////////////////////
    porAtendimento.dimensoes.x1 = dX1/2;                            //
    porAtendimento.dimensoes.y1 = dY1*1.5;                          //
    porAtendimento.dimensoes.x2 = dX2/1.4;                          //
    porAtendimento.dimensoes.y2 = dY2;                              //
    strcpy(porAtendimento.titulo, "N° de Pacientes por fila");      ///////
    porAtendimento.tamanhoDaSerie =3;                               //////////// Relatorio de atendimento por Fila
    strcpy(porAtendimento.dados[0].rotulo, "Normal");               ///////
    strcpy(porAtendimento.dados[1].rotulo, "Prioritaria");          //
    strcpy(porAtendimento.dados[2].rotulo, "Emergencial");          //
    porAtendimento.dados[0].valor = contaFila(fila,0);              //
    porAtendimento.dados[1].valor = contaFila(fila,1);              //
    porAtendimento.dados[2].valor = contaFila(fila,2);              //
//////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////
    porTempoAtendimento.dimensoes.x1 = dX1+620;                              //
    porTempoAtendimento.dimensoes.y1 = dY1*1.5;                              //
    porTempoAtendimento.dimensoes.x2 = dX2*1.7;                              //
    porTempoAtendimento.dimensoes.y2 = dY2;                              //
    strcpy(porTempoAtendimento.titulo, "Pacientes por tempo em Fila");///////
    porTempoAtendimento.tamanhoDaSerie =3;                               //////////// Relatorio de tempo por Fila
    strcpy(porTempoAtendimento.dados[0].rotulo, "Normal");               ///////
    strcpy(porTempoAtendimento.dados[1].rotulo, "Prioritaria");          //
    strcpy(porTempoAtendimento.dados[2].rotulo, "Emergencial");          //
    porTempoAtendimento.dados[0].valor = contarTempoFila(fila,0);        //
    porTempoAtendimento.dados[1].valor = contarTempoFila(fila,1);        //
    porTempoAtendimento.dados[2].valor = contarTempoFila(fila,2);        //
///////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////
    porMedico.dimensoes.x1 = dX1/2;                                //
    porMedico.dimensoes.y1 = dY1*1.5;                              //
    porMedico.dimensoes.x2 = dX2-100;                              //
    porMedico.dimensoes.y2 = dY2;                                  //
    strcpy(porMedico.titulo, "Pacientes por Medico");              //
    porMedico.tamanhoDaSerie =5;                                   //
    strcpy(porMedico.dados[0].rotulo, "Cardiologista");            //////
    strcpy(porMedico.dados[1].rotulo, "Pediatra");             ////////// Relatorio geral dos Médicos
    strcpy(porMedico.dados[2].rotulo, "Oftamologista");            //////
    strcpy(porMedico.dados[3].rotulo, "    Neurologista");             //
    strcpy(porMedico.dados[4].rotulo, "    Dermatologista");          //
    porMedico.dados[0].valor = contaMedico(fila,0);                //
    porMedico.dados[1].valor = contaMedico(fila,1);                //
    porMedico.dados[2].valor = contaMedico(fila,2);                //
    porMedico.dados[3].valor = contaMedico(fila,3);                //
    porMedico.dados[4].valor = contaMedico(fila,4);                //
/////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
    porTempoMedico.dimensoes.x1 = dX1+620;                                  //
    porTempoMedico.dimensoes.y1 = dY1*1.5;                                  //
    porTempoMedico.dimensoes.x2 = dX2*1.8;                                  //
    porTempoMedico.dimensoes.y2 = dY2;                                      //
    strcpy(porTempoMedico.titulo, "Tempo de pacientes em Medico");          //
    porTempoMedico.tamanhoDaSerie =5;                                       //
    strcpy(porTempoMedico.dados[0].rotulo, "Cardiologista");                //////
    strcpy(porTempoMedico.dados[1].rotulo, "Pediatra");                     ////////// Relatorio geral Tempo dos Médicos
    strcpy(porTempoMedico.dados[2].rotulo, "Oftamologista");                //////
    strcpy(porTempoMedico.dados[3].rotulo, "    Neurologista");                 //
    strcpy(porTempoMedico.dados[4].rotulo, "    Dermatologista");               //
    porTempoMedico.dados[0].valor = contarTempoMedico(fila,-1,0);           //
    porTempoMedico.dados[1].valor = contarTempoMedico(fila,-1,1);           //
    porTempoMedico.dados[2].valor = contarTempoMedico(fila,-1,2);           //
    porTempoMedico.dados[3].valor = contarTempoMedico(fila,-1,3);           //
    porTempoMedico.dados[4].valor = contarTempoMedico(fila,-1,4);           //
//////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////
    porExameCardiologista.dimensoes.x1 = dX1/2;                                       //
    porExameCardiologista.dimensoes.y1 = dY1*1.5;                                       //
    porExameCardiologista.dimensoes.x2 = dX2/1.1;                                       //
    porExameCardiologista.dimensoes.y2 = dY2;                                       //
    strcpy(porExameCardiologista.titulo, "Exames realizados - Cardiologista"); //////
    porExameCardiologista.tamanhoDaSerie =5;                                        ////////// Relatorio do Cardiologista
    strcpy(porExameCardiologista.dados[0].rotulo, "Exame de Rotina");               //////
    strcpy(porExameCardiologista.dados[1].rotulo, "     Ecografia");                //
    strcpy(porExameCardiologista.dados[2].rotulo, "Cintilografia miocárdica");      //
    porExameCardiologista.dados[0].valor = contaExame(fila,0,0);                    //
    porExameCardiologista.dados[1].valor = contaExame(fila,1,0);
    porExameCardiologista.dados[2].valor = contaExame(fila,2,0);                     //
//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
    porTempoCardiologista.dimensoes.x1 = dX1+620;                                   //
    porTempoCardiologista.dimensoes.y1 = dY1*1.5;                                   //
    porTempoCardiologista.dimensoes.x2 = dX2*1.75;                                   //
    porTempoCardiologista.dimensoes.y2 = dY2;                                       //
    strcpy(porTempoCardiologista.titulo, "Tempo em cada exame realizado - Cardiologista");      //////
    porTempoCardiologista.tamanhoDaSerie =5;                                        ////////// Relatorio Tempo Exames Cardiologista
    strcpy(porTempoCardiologista.dados[0].rotulo, "Exame de Rotina");               //////
    strcpy(porTempoCardiologista.dados[1].rotulo, "     Ecografia");                //
    strcpy(porTempoCardiologista.dados[2].rotulo, "Cintilografia miocárdica");      //
    porTempoCardiologista.dados[0].valor = contarTempoMedico(fila,0,0);             //
    porTempoCardiologista.dados[1].valor = contarTempoMedico(fila,1,0);             //
    porTempoCardiologista.dados[2].valor = contarTempoMedico(fila,2,0);             //
//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
    porExamePediatra.dimensoes.x1 = dX1/2;                                    //
    porExamePediatra.dimensoes.y1 = dY1*1.5;                                    //
    porExamePediatra.dimensoes.x2 = dX2/1.1;                                    //
    porExamePediatra.dimensoes.y2 = dY2;                                    //
    strcpy(porExamePediatra.titulo, "Exames realizados - Pediatra");        /////
    porExamePediatra.tamanhoDaSerie =5;                                     ////////// Relatorio do Pediatra
    strcpy(porExamePediatra.dados[0].rotulo, "Exame de Rotina");            /////
    strcpy(porExamePediatra.dados[1].rotulo, "     Audiometria");           //
    strcpy(porExamePediatra.dados[2].rotulo, "Raio X de idade óssea");      //
    porExamePediatra.dados[0].valor = contaExame(fila,0,1);                 //
    porExamePediatra.dados[1].valor = contaExame(fila,1,1);                 //
    porExamePediatra.dados[2].valor = contaExame(fila,2,1);                 //
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
    porTempoPediatra.dimensoes.x1 = dX1+620;                                    //
    porTempoPediatra.dimensoes.y1 = dY1*1.5;                                    //
    porTempoPediatra.dimensoes.x2 = dX2*1.75;                                    //
    porTempoPediatra.dimensoes.y2 = dY2;                                    //
    strcpy(porTempoPediatra.titulo, "Tempo em cada exame realizado - Pediatra");        /////
    porTempoPediatra.tamanhoDaSerie =5;                                     ////////// Relatorio Tempo Exames Pediatra
    strcpy(porTempoPediatra.dados[0].rotulo, "Exame de Rotina");            /////
    strcpy(porTempoPediatra.dados[1].rotulo, "     Audiometria");           //
    strcpy(porTempoPediatra.dados[2].rotulo, "Raio X de idade óssea");      //
    porTempoPediatra.dados[0].valor = contarTempoMedico(fila,0,1);           //
    porTempoPediatra.dados[1].valor = contarTempoMedico(fila,1,1);           //
    porTempoPediatra.dados[2].valor = contarTempoMedico(fila,2,1);           //
//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
    porExameOftalmologista.dimensoes.x1 = dX1/2;                                          //
    porExameOftalmologista.dimensoes.y1 = dY1*1.5;                                          //
    porExameOftalmologista.dimensoes.x2 = dX2/1.1;                                          //
    porExameOftalmologista.dimensoes.y2 = dY2;                                          //
    strcpy(porExameOftalmologista.titulo, "Exames realizados - Oftalmologista");        //////
    porExameOftalmologista.tamanhoDaSerie =5;                                           //////////// Relatorio Oftalmologista
    strcpy(porExameOftalmologista.dados[0].rotulo, "Exame de Rotina");                  //////
    strcpy(porExameOftalmologista.dados[1].rotulo, "  Gonioscopia");                    //
    strcpy(porExameOftalmologista.dados[2].rotulo, "Paquimetria");                      //
    porExameOftalmologista.dados[0].valor = contaExame(fila,0,2);                       //
    porExameOftalmologista.dados[1].valor = contaExame(fila,1,2);                       //
    porExameOftalmologista.dados[2].valor = contaExame(fila,2,2);                       //
//////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
    porTempoOftalmologista.dimensoes.x1 = dX1+620;                                      //
    porTempoOftalmologista.dimensoes.y1 = dY1*1.5;                                      //
    porTempoOftalmologista.dimensoes.x2 = dX2*1.75;                                     //
    porTempoOftalmologista.dimensoes.y2 = dY2;                                          //
    strcpy(porTempoOftalmologista.titulo, "Tempo em cada exame realizado - Oftalmologista");        //////
    porTempoOftalmologista.tamanhoDaSerie =5;                                           //////////// Relatorio Tempo Exames Oftalmologista
    strcpy(porTempoOftalmologista.dados[0].rotulo, "Exame de Rotina");                  //////
    strcpy(porTempoOftalmologista.dados[1].rotulo, "  Gonioscopia");                    //
    strcpy(porTempoOftalmologista.dados[2].rotulo, "Paquimetria");                      //
    porTempoOftalmologista.dados[0].valor = contarTempoFila(fila,0);                 //
    porTempoOftalmologista.dados[1].valor = contarTempoFila(fila,1);                 //
    porTempoOftalmologista.dados[2].valor = contarTempoFila(fila,2);                 //
//////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
    porExameNeurologista.dimensoes.x1 = dX1/2;                                      //
    porExameNeurologista.dimensoes.y1 = dY1*1.5;                                    //
    porExameNeurologista.dimensoes.x2 = dX2/1.05;                                   //
    porExameNeurologista.dimensoes.y2 = dY2;                                        //
    strcpy(porExameNeurologista.titulo, "Exames realizados - Neurologista");        /////
    porExameNeurologista.tamanhoDaSerie =5;                                         /////////// Relatorio Neurologista
    strcpy(porExameNeurologista.dados[0].rotulo, "Exame de Rotina");                /////
    strcpy(porExameNeurologista.dados[1].rotulo, "Eletroencefalograma");            //
    strcpy(porExameNeurologista.dados[2].rotulo, "      Eletroneuromiografia");                 //
    porExameNeurologista.dados[0].valor = contaExame(fila,0,3);                     //
    porExameNeurologista.dados[1].valor = contaExame(fila,1,3);                     //
    porExameNeurologista.dados[2].valor = contaExame(fila,2,3);                     //
//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
    porTempoNeurologista.dimensoes.x1 = dX1+620;                                    //
    porTempoNeurologista.dimensoes.y1 = dY1*1.5;                                    //
    porTempoNeurologista.dimensoes.x2 = dX2*1.79;                                   //
    porTempoNeurologista.dimensoes.y2 = dY2;                                        //
    strcpy(porTempoNeurologista.titulo, "Tempo em cada exame realizado - Neurologista");        /////
    porTempoNeurologista.tamanhoDaSerie =5;                                         /////////// Relatorio Tempo Exames Neurologista
    strcpy(porTempoNeurologista.dados[0].rotulo, "Exame de Rotina");                /////
    strcpy(porTempoNeurologista.dados[1].rotulo, "Eletroencefalograma");            //
    strcpy(porTempoNeurologista.dados[2].rotulo, "      Eletroneuromiografia");                 //
    porTempoNeurologista.dados[0].valor = contarTempoMedico(fila,0,3);              //
    porTempoNeurologista.dados[1].valor = contarTempoMedico(fila,1,3);              //
    porTempoNeurologista.dados[2].valor = contarTempoMedico(fila,2,3);              //
//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
    porExameDermatologista.dimensoes.x1 = dX1/2;                                    //
    porExameDermatologista.dimensoes.y1 = dY1*1.5;                                  //
    porExameDermatologista.dimensoes.x2 = dX2/1.1;                                  //
    porExameDermatologista.dimensoes.y2 = dY2;                                      //
    strcpy(porExameDermatologista.titulo, "Exames realizados - Dermatologista");    //////
    porExameDermatologista.tamanhoDaSerie =5;                                       /////////// Relatorio Dermatologista
    strcpy(porExameDermatologista.dados[0].rotulo, "Exame de Rotina");              //////
    strcpy(porExameDermatologista.dados[1].rotulo, "Exame Micológico");             //
    strcpy(porExameDermatologista.dados[2].rotulo, "    Exame Bacteriológico");         //
    porExameDermatologista.dados[0].valor = contaExame(fila,0,4);                   //
    porExameDermatologista.dados[1].valor = contaExame(fila,1,4);                   //
    porExameDermatologista.dados[2].valor = contaExame(fila,2,4);                   //
//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
    porTempoDermatologista.dimensoes.x1 = dX1+620;                                  //
    porTempoDermatologista.dimensoes.y1 = dY1*1.5;                                  //
    porTempoDermatologista.dimensoes.x2 = dX2*1.75;                                 //
    porTempoDermatologista.dimensoes.y2 = dY2;                                      //
    strcpy(porTempoDermatologista.titulo, "Tempo em cada exame realizado - Dermatologista");    //////
    porTempoDermatologista.tamanhoDaSerie =5;                                       /////////// Relatorio Tempo Exames Dermatologista
    strcpy(porTempoDermatologista.dados[0].rotulo, "Exame de Rotina");              //////
    strcpy(porTempoDermatologista.dados[1].rotulo, "Exame Micológico");             //
    strcpy(porTempoDermatologista.dados[2].rotulo, "    Exame Bacteriológico");         //
    porTempoDermatologista.dados[0].valor = contarTempoMedico(fila,0,4);            //
    porTempoDermatologista.dados[1].valor = contarTempoMedico(fila,1,4);            //
    porTempoDermatologista.dados[2].valor = contarTempoMedico(fila,2,4);            //
//////////////////////////////////////////////////////////////////////////////////////


    clearmouseclick(WM_LBUTTONDOWN);
    cleardevice();
    readimagefile("relatorios.jpg", 0, 0, 1360, 695);
    criaMenuRelatorios();

    while(fim!=1)
{
//////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 45 && mousex() <= 45+176 &&                               //
                mousey() >= 305 && mousey() <= 305+52)                              //
        {                                                                           //
            readimagefile("relatorioSoftware2.jpg", 45, 305, 45+176, 305+52);    //
            if (mousex() >= 45 && mousex() <= 45+176 &&                             //
                    mousey() >= 305 && mousey() <= 305+52 &&                        //
                    ismouseclick(WM_LBUTTONDOWN))                                   //
            {                                                                       //
                cleardevice();
                readimagefile("relatorioSoftwareBG.jpg",0,0,1360,695);              //
                readimagefile("back.jpg", 0, 0, 166, 51);                           //
                relatorioSoftware(fila,tempoExecucao);                              //
                clearmouseclick(WM_LBUTTONDOWN);                                    ///////
                while(voltar!=1)                                                    //////////// Botão Relatorio Software
                {                                                                   //////
                    if (mousex() >= 0 && mousex() <= 50 &&                          //
                            mousey() >= 0 && mousey() <= 166 &&                     //
                            ismouseclick(WM_LBUTTONDOWN))                           //
                    {                                                               //
                        voltar=1;                                                   //
                        cleardevice();                                              //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);            //
                        criaMenuRelatorios();                                       //
                    }                                                               //
                };                                                                  //
                voltar=0;                                                           //
                clearmouseclick(WM_LBUTTONDOWN);                                    //
            }                                                                       //
        };                                                                          //
/////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 45 && mousex() <= 45+176 &&                               //
                mousey() >= 195 && mousey() <= 195+52)                              //
        {                                                                           //
            readimagefile("relatorioAtendimento2.jpg", 45, 195, 45+176, 195+52);    //
            if (mousex() >= 45 && mousex() <= 45+176 &&                             //
                    mousey() >= 195 && mousey() <= 195+52 &&                        //
                    ismouseclick(WM_LBUTTONDOWN))                                   //
            {                                                                       //
                cleardevice();                                                      //
                readimagefile("back.jpg", 0, 0, 166, 51);                           //
                barChart(porAtendimento);
                barChart(porTempoAtendimento);                                       //
                clearmouseclick(WM_LBUTTONDOWN);                                    ///////
                while(voltar!=1)                                                    //////////// Botão Relatorio Atendimento
                {                                                                   //////
                    if (mousex() >= 0 && mousex() <= 50 &&                          //
                            mousey() >= 0 && mousey() <= 166 &&                     //
                            ismouseclick(WM_LBUTTONDOWN))                           //
                    {                                                               //
                        voltar=1;                                                   //
                        cleardevice();                                              //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);            //
                        criaMenuRelatorios();                                       //
                    }                                                               //
                };                                                                  //
                voltar=0;                                                           //
                clearmouseclick(WM_LBUTTONDOWN);                                    //
            }                                                                       //
        };                                                                          //
/////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 45 && mousex() <= 221 &&                                  //
                mousey() >= 250 && mousey() <= 250+52)                              //
        {                                                                           //
            readimagefile("relatorioMedicos2.jpg", 45, 250, 45+176, 250+52);        //
            if (mousex() >= 45 && mousex() <= 221 &&                                //
                    mousey() >= 250 && mousey() <= 250+52 &&                        //
                    ismouseclick(WM_LBUTTONDOWN))                                   //
            {                                                                       //
                cleardevice();                                                      //
                readimagefile("back.jpg", 0, 0, 166, 51);                           //
                barChart(porMedico);
                barChart(porTempoMedico);                                           //
                clearmouseclick(WM_LBUTTONDOWN);                                    //
                while(voltar!=1)                                                    //
                {                                                                   //////
                    if (mousex() >= 0 && mousex() <= 50 &&                          /////////// Botão Relatorio geral dos Medicos
                            mousey() >= 0 && mousey() <= 166 &&                     //////
                            ismouseclick(WM_LBUTTONDOWN))                           //
                    {                                                               //
                        voltar=1;                                                   //
                        cleardevice();                                              //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);
                        criaMenuRelatorios();                                       //
                    }                                                               //
                };                                                                  //
                voltar=0;                                                           //
                clearmouseclick(WM_LBUTTONDOWN);                                    //
            }                                                                       //
        };                                                                          //
//////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 450 && mousex() <= 450+176 &&                                 //
                mousey() >= 195 && mousey() <= 195+52)                                  //
        {                                                                               //
            readimagefile("relatorioCardiologista2.jpg", 450, 195, 450+176, 195+52);    //
            if (mousex() >= 450 && mousex() <= 450+176 &&                               //
                    mousey() >= 195 && mousey() <= 195+52 &&                            //
                    ismouseclick(WM_LBUTTONDOWN))                                       //
            {                                                                           //
                cleardevice();                                                          //
                readimagefile("back.jpg", 0, 0, 166, 51);                               //
                barChart(porExameCardiologista);
                barChart(porTempoCardiologista);                                        ///////
                clearmouseclick(WM_LBUTTONDOWN);                                        //////////// Botão Relatorio Cardiologista
                while(voltar!=1)                                                        //////
                {                                                                       //
                    if (mousex() >= 0 && mousex() <= 50 &&                              //
                            mousey() >= 0 && mousey() <= 166 &&                         //
                            ismouseclick(WM_LBUTTONDOWN))                               //
                    {                                                                   //
                        voltar=1;                                                       //
                        cleardevice();                                                  //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);               //
                        criaMenuRelatorios();                                           //
                    }                                                                   //
                };                                                                      //
                voltar=0;                                                               //
                clearmouseclick(WM_LBUTTONDOWN);                                        //
            }                                                                           //
        }                                                                               //
//////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 450 && mousex() <= 450+176 &&                                 //
                mousey() >= 250 && mousey() <= 250+52)                                  //
        {                                                                               //
            readimagefile("relatorioPediatra2.jpg", 450, 250, 450+176, 250+52);         //
            if (mousex() >= 450 && mousex() <= 450+176 &&                               //
                    mousey() >= 250 && mousey() <= 250+52 &&                            //
                    ismouseclick(WM_LBUTTONDOWN))                                       //
            {                                                                           //
                cleardevice();                                                          //
                readimagefile("back.jpg", 0, 0, 166, 51);                               //
                barChart(porExamePediatra);
                barChart(porTempoPediatra);                                             //
                clearmouseclick(WM_LBUTTONDOWN);                                        ////////
                while(voltar!=1)                                                        //////////// Botão Relatorio Pediatra
                {                                                                       ////////
                    if (mousex() >= 0 && mousex() <= 50 &&                              //
                            mousey() >= 0 && mousey() <= 166 &&                         //
                            ismouseclick(WM_LBUTTONDOWN))                               //
                    {                                                                   //
                        voltar=1;                                                       //
                        cleardevice();                                                  //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);               //
                        criaMenuRelatorios();                                           //
                    }                                                                   //
                };                                                                      //
                voltar=0;                                                               //
                clearmouseclick(WM_LBUTTONDOWN);                                        //
            }                                                                           //
        }                                                                               //
//////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 450 && mousex() <= 450+176 &&                                 //
                mousey() >= 305 && mousey() <= 305+52)                                  //
        {                                                                               //
            readimagefile("relatorioOftalmologista2.jpg", 450, 305, 450+176, 305+52);   //
            if (mousex() >= 450 && mousex() <= 450+176 &&                               //
                    mousey() >= 305 && mousey() <= 305+52 &&                            //
                    ismouseclick(WM_LBUTTONDOWN))                                       //
            {                                                                           //
                cleardevice();                                                          //
                readimagefile("back.jpg", 0, 0, 166, 51);                               //
                barChart(porExameOftalmologista);
                barChart(porTempoOftalmologista);                                       //
                clearmouseclick(WM_LBUTTONDOWN);                                        //
                while(voltar!=1)                                                        ////////
                {                                                                       /////////////// Botão Relatorio Oftalmologista
                    if (mousex() >= 0 && mousex() <= 50 &&                              ////////
                            mousey() >= 0 && mousey() <= 166 &&                         //
                            ismouseclick(WM_LBUTTONDOWN))                               //
                    {                                                                   //
                        voltar=1;                                                       //
                        cleardevice();                                                  //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);               //
                        criaMenuRelatorios();                                           //
                    }                                                                   //
                };                                                                      //
                voltar=0;                                                               //
                clearmouseclick(WM_LBUTTONDOWN);                                        //
            }                                                                           //
        }                                                                               //
//////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 450 && mousex() <= 450+176 &&                                 //
                mousey() >= 360 && mousey() <= 360+52)                                  //
        {                                                                               //
            readimagefile("relatorioNeurologista2.jpg", 450, 360, 450+176, 360+52);     //
            if (mousex() >= 450 && mousex() <= 450+176 &&                               //
                    mousey() >= 360 && mousey() <= 360+52 &&                            //
                    ismouseclick(WM_LBUTTONDOWN))                                       //
            {                                                                           //
                cleardevice();                                                          //
                readimagefile("back.jpg", 0, 0, 166, 51);                               //
                barChart(porExameNeurologista);
                barChart(porTempoNeurologista);                                         //////
                clearmouseclick(WM_LBUTTONDOWN);                                        //////////// Botão Relatorio Neurologista
                while(voltar!=1)                                                        //////
                {                                                                       //
                    if (mousex() >= 0 && mousex() <= 50 &&                              //
                            mousey() >= 0 && mousey() <= 166 &&                         //
                            ismouseclick(WM_LBUTTONDOWN))                               //
                    {                                                                   //
                        voltar=1;                                                       //
                        cleardevice();                                                  //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);               //
                        criaMenuRelatorios();                                           //
                    }                                                                   //
                };                                                                      //
                voltar=0;                                                               //
                clearmouseclick(WM_LBUTTONDOWN);                                        //
            }                                                                           //
        }                                                                               //
//////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 450 && mousex() <= 450+176 &&                                 //
                mousey() >= 415 && mousey() <= 415+52)                                  //
        {                                                                               //
            readimagefile("relatorioDermatologista2.jpg", 450, 415, 450+176, 415+52);   //
            if (mousex() >= 450 && mousex() <= 450+176 &&                               //
                    mousey() >= 415 && mousey() <= 415+52 &&                            //
                    ismouseclick(WM_LBUTTONDOWN))                                       //
            {                                                                           //
                cleardevice();                                                          //
                readimagefile("back.jpg", 0, 0, 166, 51);                               ////////
                barChart(porExameDermatologista);
                barChart(porTempoDermatologista);                                       ///////////// Botão Relatorio Dermatologista
                clearmouseclick(WM_LBUTTONDOWN);                                        ////////
                while(voltar!=1)                                                        //
                {                                                                       //
                    if (mousex() >= 0 && mousex() <= 50 &&                              //
                            mousey() >= 0 && mousey() <= 166 &&                         //
                            ismouseclick(WM_LBUTTONDOWN))                               //
                    {                                                                   //
                        voltar=1;                                                       //
                        cleardevice();                                                  //
                        readimagefile("relatorios.jpg", 0, 0, 1360, 695);                //
                        criaMenuRelatorios();                                           //
                    }                                                                   //
                };                                                                      //
                voltar=0;                                                               //
                clearmouseclick(WM_LBUTTONDOWN);                                        //
            }                                                                           //
        }                                                                               //
//////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////
        while(mousex() >= 1184 && mousex() <= 1184+176 &&                                 //
                mousey() >= 643 && mousey() <= 643+52)                                  //
        {                                                                               //
             readimagefile("finalizar2.jpg", 1184, 643, 1184+176, 643+52);                 //
            if (mousex() >= 1184 && mousex() <= 1184+176 &&                               /////////
                    mousey() >= 643 && mousey() <= 643+52 &&                            ////////////// Botão de Finalizar
                    ismouseclick(WM_LBUTTONDOWN))                                       ////////
            {                                                                           //
                fim=1;                                                                  //
                break;                                                                  //
            }                                                                           //
        }                                                                               //
//////////////////////////////////////////////////////////////////////////////////////////
        Sleep(350);
        clearmouseclick(WM_LBUTTONDOWN);
        criaMenuRelatorios();
    }
    return;
}

void iniciarSimulacao(filaPaciente *fila,int menuF,int menuM,int exame,int aleatorio,int telaDescricao,int telaPrincipal)
{
    Pacientes paciente;
    char auxExame[3]={0};
    paciente.horaChegada=getTimeSystem();
    if(aleatorio==1)
    {
        paciente.exame=rand() % 3;
        paciente.senhaPaciente.tipoFila = rand() % 3;
        paciente.senhaPaciente.tipoMedico = rand () % 5;
        setcurrentwindow(telaDescricao);
        plotaDescricao(paciente.senhaPaciente.tipoMedico+1,paciente.exame+1);
        setcurrentwindow(telaPrincipal);
        if(paciente.senhaPaciente.tipoFila==0)
            paciente.senhaPaciente.numSenha=contaFila(*fila,0);
        if(paciente.senhaPaciente.tipoFila==1)
            paciente.senhaPaciente.numSenha=contaFila(*fila,1);
        if(paciente.senhaPaciente.tipoFila==2)
            paciente.senhaPaciente.numSenha=contaFila(*fila,2);

        if(paciente.senhaPaciente.tipoMedico==0)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(0);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("cardiologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);
        }
         if(paciente.senhaPaciente.tipoMedico==1)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(1);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("pediatra.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);
        }
        if(paciente.senhaPaciente.tipoMedico==2)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(2);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("oftalmologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);
        }
        if(paciente.senhaPaciente.tipoMedico==3)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(3);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("neurologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);
        }
        if(paciente.senhaPaciente.tipoMedico==4)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(4);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("dermatologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);
        }

        Sleep(ESPERAR);

    }
    else
    {
        paciente.exame=exame;
        if(menuF==0)
        {
            paciente.senhaPaciente.numSenha=contaFila(*fila,0);
            paciente.senhaPaciente.tipoFila=0;
            paciente.senhaPaciente.tipoMedico=menuM;
        }
        if(menuF==1)
        {
            paciente.senhaPaciente.numSenha=contaFila(*fila,1);
            paciente.senhaPaciente.tipoFila=1;
            paciente.senhaPaciente.tipoMedico=menuM;
        }
        if(menuF==2)
        {
            paciente.senhaPaciente.numSenha=contaFila(*fila,2);
            paciente.senhaPaciente.tipoFila=2;
            paciente.senhaPaciente.tipoMedico=menuM;
        }
        if(menuM==0)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(0);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("cardiologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);
        }
        if(menuM==1)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(1);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("pediatra.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);

        }
        if(menuM==2)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(2);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("oftalmologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);

        }
        if(menuM==3)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(3);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("neurologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);


        }
        if(menuM==4)
        {
            cleardevice();
            readimagefile("cenarioMedico.jpg",0,0,800,600);
            outtextxy(25,267,"Senha:");
            printaSenha(paciente);
            andaPaciente(4);
            Sleep(ESPERAR);
            cleardevice();
            readimagefile("dermatologista.jpg",0,0,800,600);
            itoa(paciente.exame+1,auxExame,10);// Converte INT para String
            outtextxy(540,480,"Sua Opção: ");
            outtextxy(620,480,auxExame);
            Sleep(ESPERAR);


        }
        Sleep(ESPERAR);
    }
   paciente.horaSaida=getTimeSystem();
   push(fila,paciente);

    if(aleatorio==0)
        readimagefile("cenario.jpg",0,0,800,600);
    else
        readimagefile("cenarioAleatorio.jpg",0,0,800,600);
}

void initMenu(filaPaciente *fila, int telaPrincipal, int telaSenha, int telaDescricao)
{
    char rotulo[30];
    int exame=0;
    int sair=0;
    int aleatorio=0;
    int simula=0;
    int menuMedico=0;
    int menuFila=0;
    criarMenu(); // Desenha botões do menuGerarSenha

    do
    {
    setcurrentwindow(telaDescricao);
    plotaDescricao(menuMedico,exame);
    setcurrentwindow(telaSenha);
        while(mousex() >= 10 && mousex() <= 20 && mousey() >= 440 && mousey() <= 460)
        {
            if (mousex() >= 10 && mousex() <= 20 &&
                    mousey() >= 440 && mousey() <= 460 &&
                    ismouseclick(WM_LBUTTONDOWN) && !aleatorio)
            {
                strcpy(rotulo, "Gerar Aleatoriamente");
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 440, BGCOR, COR,2);
                aleatorio=1;
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);

            }

            if (mousex() >= 10 && mousex() <= 20 &&
                    mousey() >= 440 && mousey() <= 460 &&
                    ismouseclick(WM_LBUTTONDOWN) && aleatorio)
            {
                strcpy(rotulo, "Gerar Aleatoriamente");
                drawRadioButtonUnchecked(rotulo, 10, 440, BGCOR, COR);
                aleatorio=0;
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
        Sleep(BUFF);
        }
        while(mousex() >= 0 && mousex() <= 20 && mousey() >= 50 && mousey() <= 70)
        {

            if (mousex() >= 0 && mousex() <= 20 &&
                    mousey() >= 50 && mousey() <= 70 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuFila)
            {
                strcpy(rotulo, "Normal");
                menuFila = 1;
                exame=0;
                menuMedico=0;
                aleatorio=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);

            }

            if (mousex() >= 0 && mousex() <= 20 &&
                    mousey() >= 50 && mousey() <= 70 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuFila)
            {
                strcpy(rotulo, "Normal");
                drawRadioButtonUnchecked(rotulo, 10, 50, BGCOR, COR);
                menuFila = 0;
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

        Sleep(BUFF);
        }
        while(mousex() >= 80 && mousex() <= 100 &&
                mousey() >= 50 && mousey() <= 70)
        {

            if (mousex() >= 80 && mousex() <= 100 &&
                    mousey() >= 50 && mousey() <= 70 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuFila)
            {
                strcpy(rotulo, "Prioritaria");
                menuFila = 2;
                exame=0;
                menuMedico=0;
                aleatorio=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 80 && mousex() <= 100 &&
                    mousey() >= 50 && mousey() <= 70 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuFila)
            {
                strcpy(rotulo, "Prioritaria");
                drawRadioButtonUnchecked(rotulo, 90, 50, BGCOR, COR);
                menuFila = 0;
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }

        while(mousex() >= 180 && mousex() <= 200 &&
                mousey() >= 50 && mousey() <= 70)
        {

            if (mousex() >= 180 && mousex() <= 200 &&
                    mousey() >= 50 && mousey() <= 70 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuFila)
            {
                strcpy(rotulo, "Emergencial");
                menuFila = 3;
                exame=0;
                menuMedico=0;
                aleatorio=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 180 && mousex() <= 200 &&
                    mousey() >= 50 && mousey() <= 70 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuFila)
            {
                strcpy(rotulo, "Emergencial");
                drawRadioButtonUnchecked(rotulo, 190, 50, BGCOR, COR);
                menuFila = 0;
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);

        }
        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 150 && mousey() <= 170)
        {

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 150 && mousey() <= 170 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuMedico)
            {
                strcpy(rotulo, "Cardiologista");
                menuMedico = 1;
                exame=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }

                strcpy(rotulo, "Exame de Rotina");
                drawRadioButton(rotulo, 10, 300, BGCOR, COR);
                strcpy(rotulo, "Ecografia transesofágica");
                drawRadioButton(rotulo, 10, 320, BGCOR, COR);
                strcpy(rotulo, "Cintilografia miocárdica");
                drawRadioButton(rotulo, 10, 340, BGCOR, COR);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 150 && mousey() <= 170 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuMedico)
            {
                menuMedico = 0;
                exame=0;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }
        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 170 && mousey() <= 190)
        {

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 170 && mousey() <= 190 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuMedico)
            {
                strcpy(rotulo, "Pediatra");
                menuMedico = 2;
                exame=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }

                strcpy(rotulo, "Exame de Rotina");
                drawRadioButton(rotulo, 10, 300, BGCOR, COR);
                strcpy(rotulo, "Audiometria");
                drawRadioButton(rotulo, 10, 320, BGCOR, COR);
                strcpy(rotulo, "Raio X de idade óssea");
                drawRadioButton(rotulo, 10, 340, BGCOR, COR);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 170 && mousey() <= 190 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuMedico)
            {
                menuMedico = 0;
                exame=0;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }

        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 190 && mousey() <= 210)
        {

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 190 && mousey() <= 210 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuMedico)
            {
                strcpy(rotulo, "Oftalmologista");
                menuMedico = 3;
                exame=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }

                strcpy(rotulo, "Exame de Rotina");
                drawRadioButton(rotulo, 10, 300, BGCOR, COR);
                strcpy(rotulo, "Gonioscopia");
                drawRadioButton(rotulo, 10, 320, BGCOR, COR);
                strcpy(rotulo, "Paquimetria");
                drawRadioButton(rotulo, 10, 340, BGCOR, COR);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 190 && mousey() <= 210 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuMedico)
            {
                menuMedico = 0;
                exame=0;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }
        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 210 && mousey() <= 230)
        {


            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 210 && mousey() <= 230 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuMedico)
            {
                strcpy(rotulo, "Neurologista");
                menuMedico = 4;
                exame=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }

                strcpy(rotulo, "Exame de Rotina");
                drawRadioButton(rotulo, 10, 300, BGCOR, COR);
                strcpy(rotulo, "Eletroencefalograma");
                drawRadioButton(rotulo, 10, 320, BGCOR, COR);
                strcpy(rotulo, "Eletroneuromiografia");
                drawRadioButton(rotulo, 10, 340, BGCOR, COR);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 210 && mousey() <= 230 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuMedico)
            {
                menuMedico = 0;
                exame=0;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            Sleep(BUFF);

        }
        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 230 && mousey() <= 250)
        {

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 230 && mousey() <= 250 &&
                    ismouseclick(WM_LBUTTONDOWN) && !menuMedico)
            {
                strcpy(rotulo, "Dermatologista");
                menuMedico = 5;
                exame=0;
                cleardevice();
                criarMenu();
                drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }

                strcpy(rotulo, "Exame de Rotina");
                drawRadioButton(rotulo, 10, 300, BGCOR, COR);
                strcpy(rotulo, "Exame Micológico");
                drawRadioButton(rotulo, 10, 320, BGCOR, COR);
                strcpy(rotulo, "Exame Bacteriológico");
                drawRadioButton(rotulo, 10, 340, BGCOR, COR);
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 230 && mousey() <= 250 &&
                    ismouseclick(WM_LBUTTONDOWN) && menuMedico)
            {
                menuMedico = 0;
                exame=0;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }

            Sleep(BUFF);
        }

/////////////////////////////////////////////////////////////////////////
        while(mousex() >= 10 && mousex() <= 30 &&                        ////
                mousey() >= 300 && mousey() <= 320)
        {

            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 300 && mousey() <= 320 &&
                    ismouseclick(WM_LBUTTONDOWN) && !exame)
            {
                exame = 1;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                if(menuMedico==1)
                {
                    strcpy(rotulo, "Cardiologista");
                    drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                    strcpy(rotulo, "Cintilografia miocárdica");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Ecografia transesofágica");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                }

                if(menuMedico==2)
                {
                    strcpy(rotulo, "Pediatra");
                    drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                    strcpy(rotulo, "Raio X de idade óssea");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Audiometria");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                }
                if(menuMedico==3)
                {
                    strcpy(rotulo, "Oftalmologista");
                    drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                    strcpy(rotulo, "Paquimetria");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                }

                if(menuMedico==4)
                {
                    strcpy(rotulo, "Neurologista");
                    drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroneuromiografia");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                }

                if(menuMedico==5)
                {
                    strcpy(rotulo, "Dermatologista");
                    drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Bacteriológico");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Exame Micológico");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                }
                if(menuMedico>0)
                {
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonChecked(rotulo, 10, 300, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 300 && mousey() <= 320 &&
                    ismouseclick(WM_LBUTTONDOWN) && exame)
            {
                exame = 0;

                if(menuMedico==1)
                {
                    strcpy(rotulo, "Cardiologista");
                    drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                    strcpy(rotulo, "Cintilografia miocárdica");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Ecografia transesofágica");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==2)
                {
                    strcpy(rotulo, "Pediatra");
                    drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                    strcpy(rotulo, "Raio X de idade óssea");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Audiometria");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                if(menuMedico==3)
                {
                    strcpy(rotulo, "Oftalmologista");
                    drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                    strcpy(rotulo, "Paquimetria");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==4)
                {
                    strcpy(rotulo, "Neurologista");
                    drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroneuromiografia");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==5)
                {
                    strcpy(rotulo, "Dermatologista");
                    drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Bacteriológico");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Exame Micológico");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }
        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 325 && mousey() <= 340)
        {


            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 325 && mousey() <= 340 &&
                    ismouseclick(WM_LBUTTONDOWN) && !exame)
            {
                exame = 2;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                if(menuMedico==1)
                {
                    strcpy(rotulo, "Cardiologista");
                    drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                    strcpy(rotulo, "Cintilografia miocárdica");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Ecografia transesofágica");
                    drawRadioButtonChecked(rotulo, 10, 320, BGCOR, COR,2);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==2)
                {
                    strcpy(rotulo, "Pediatra");
                    drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                    strcpy(rotulo, "Raio X de idade óssea");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Audiometria");
                    drawRadioButtonChecked(rotulo, 10, 320, BGCOR, COR,2);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                if(menuMedico==3)
                {
                    strcpy(rotulo, "Oftalmologista");
                    drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                    strcpy(rotulo, "Paquimetria");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonChecked(rotulo, 10, 320, BGCOR, COR,2);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==4)
                {
                    strcpy(rotulo, "Neurologista");
                    drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroneuromiografia");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Eletroencefalograma");
                    drawRadioButtonChecked(rotulo, 10, 320, BGCOR, COR,2);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==5)
                {
                    strcpy(rotulo, "Dermatologista");
                    drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Bacteriológico");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Exame Micológico");
                    drawRadioButtonChecked(rotulo, 10, 320, BGCOR, COR,2);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 325 && mousey() <= 340 &&
                    ismouseclick(WM_LBUTTONDOWN) && exame)
            {

                exame = 0;
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                if(menuMedico==1)
                {
                    strcpy(rotulo, "Cardiologista");
                    drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                    strcpy(rotulo, "Cintilografia miocárdica");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Ecografia transesofágica");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==2)
                {
                    strcpy(rotulo, "Pediatra");
                    drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                    strcpy(rotulo, "Raio X de idade óssea");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Audiometria");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                if(menuMedico==3)
                {
                    strcpy(rotulo, "Oftalmologista");
                    drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                    strcpy(rotulo, "Paquimetria");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==4)
                {
                    strcpy(rotulo, "Neurologista");
                    drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroneuromiografia");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Eletroencefalograma");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==5)
                {
                    strcpy(rotulo, "Dermatologista");
                    drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Bacteriológico");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Exame Micológico");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }
        while(mousex() >= 10 && mousex() <= 30 &&
                mousey() >= 345 && mousey() <= 360)
        {


            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 345 && mousey() <= 360 &&
                    ismouseclick(WM_LBUTTONDOWN) && !exame)
            {

                exame = 3;
                cleardevice();
                criarMenu();
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                if(menuMedico==1)
                {
                    strcpy(rotulo, "Cardiologista");
                    drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                    strcpy(rotulo, "Cintilografia miocárdica");
                    drawRadioButtonChecked(rotulo, 10, 340, BGCOR, COR,2);
                    strcpy(rotulo, "Ecografia transesofágica");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==2)
                {
                    strcpy(rotulo, "Pediatra");
                    drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                    strcpy(rotulo, "Raio X de idade óssea");
                    drawRadioButtonChecked(rotulo, 10, 340, BGCOR, COR,2);
                    strcpy(rotulo, "Audiometria");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                if(menuMedico==3)
                {
                    strcpy(rotulo, "Oftalmologista");
                    drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                    strcpy(rotulo, "Paquimetria");
                    drawRadioButtonChecked(rotulo, 10, 340, BGCOR, COR,2);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==4)
                {
                    strcpy(rotulo, "Neurologista");
                    drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroneuromiografia");
                    drawRadioButtonChecked(rotulo, 10, 340, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroencefalograma");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==5)
                {
                    strcpy(rotulo, "Dermatologista");
                    drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Bacteriológico");
                    drawRadioButtonChecked(rotulo, 10, 340, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Micológico");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            if (mousex() >= 10 && mousex() <= 30 &&
                    mousey() >= 345 && mousey() <= 360 &&
                    ismouseclick(WM_LBUTTONDOWN) && exame)
            {

                exame = 0;
                if(menuFila==1)
                {
                    strcpy(rotulo, "Normal");
                    drawRadioButtonChecked(rotulo, 10, 50, BGCOR, COR,2);
                }
                if(menuFila==2)
                {
                    strcpy(rotulo, "Prioritaria");
                    drawRadioButtonChecked(rotulo, 90, 50, BGCOR, COR,2);
                }
                if(menuFila==3)
                {
                    strcpy(rotulo, "Emergencial");
                    drawRadioButtonChecked(rotulo, 190, 50, BGCOR, COR,2);
                }
                if(menuMedico==1)
                {
                    strcpy(rotulo, "Cardiologista");
                    drawRadioButtonChecked(rotulo, 10, 150, BGCOR, COR,2);
                    strcpy(rotulo, "Cintilografia miocárdica");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Ecografia transesofágica");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==2)
                {
                    strcpy(rotulo, "Pediatra");
                    drawRadioButtonChecked(rotulo, 10, 170, BGCOR, COR,2);
                    strcpy(rotulo, "Raio X de idade óssea");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Audiometria");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                if(menuMedico==3)
                {
                    strcpy(rotulo, "Oftalmologista");
                    drawRadioButtonChecked(rotulo, 10, 190, BGCOR, COR,2);
                    strcpy(rotulo, "Paquimetria");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Gonioscopia");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==4)
                {
                    strcpy(rotulo, "Neurologista");
                    drawRadioButtonChecked(rotulo, 10, 210, BGCOR, COR,2);
                    strcpy(rotulo, "Eletroneuromiografia");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Eletroencefalograma");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }

                if(menuMedico==5)
                {
                    strcpy(rotulo, "Dermatologista");
                    drawRadioButtonChecked(rotulo, 10, 230, BGCOR, COR,2);
                    strcpy(rotulo, "Exame Bacteriológico");
                    drawRadioButtonUnchecked(rotulo, 10, 340, BGCOR, COR);
                    strcpy(rotulo, "Exame Micológico");
                    drawRadioButtonUnchecked(rotulo, 10, 320, BGCOR, COR);
                    strcpy(rotulo, "Exame de Rotina");
                    drawRadioButtonUnchecked(rotulo, 10, 300, BGCOR, COR);
                }
                Sleep(BUFF);
                clearmouseclick(WM_LBUTTONDOWN);
            }
            Sleep(BUFF);
        }
        if(menuFila!=0 && menuMedico!=0 && exame!=0)
        {
            readimagefile("iniciarSimulacao.jpg", 5, 370, 5+166, 370+51);
            while(mousex() >= 5 && mousex() <= 5+166 &&
                    mousey() >= 370 && mousey() <= 370+51)
            {
                if (mousex() >= 5 && mousex() <= 5+166 &&
                        mousey() >= 370 && mousey() <= 370+51)
                    readimagefile("iniciarSimulacao2.jpg", 5, 370, 5+166, 370+51);
                if (mousex() >= 5 && mousex() <= 5+166 &&
                        mousey() >= 370 && mousey() <= 370+51 && ismouseclick(WM_LBUTTONDOWN))
                {
                    simula=1;
                    Sleep(BUFF);
                    readimagefile("iniciarSimulacao.jpg", 5, 370, 5+166, 370+51);
                    break;
                }
            }
        }
        while(mousex() >= 175 && mousex() <= 175+110 &&
                mousey() >= 370 && mousey() <= 370+51)
        {
            if (mousex() >= 175 && mousex() <= 175+110 &&
                    mousey() >= 370 && mousey() <= 370+51)
                readimagefile("sair2.jpg", 175, 370, 175+110, 370+51);
            if (mousex() >= 175 && mousex() <= 175+110 &&
                    mousey() >= 370 && mousey() <= 370+51 && ismouseclick(WM_LBUTTONDOWN))
                sair=1;
        }
        clearmouseclick(WM_LBUTTONDOWN);
        Sleep(80);
        if(aleatorio==1)
        {
            setcurrentwindow(telaPrincipal);
            iniciarSimulacao(fila,menuFila,menuMedico,exame,aleatorio,telaDescricao,telaPrincipal);
            setcurrentwindow(telaSenha);
            Sleep(ESPERAR);
        }
        if(simula==1)
        {
            setcurrentwindow(telaPrincipal);
            aleatorio=0;
            iniciarSimulacao(fila,menuFila-1,menuMedico-1,exame-1,aleatorio,telaDescricao,telaPrincipal);
            setcurrentwindow(telaSenha);
            cleardevice();
            criarMenu();
            menuFila=0;
            menuMedico=0;
            simula=0;
            exame=0;
            Sleep(BUFF);
        }
        Sleep(BUFF);
    }
    while(sair==0);
    return;
}

void criarMenu()
{
    char rotulo[30];
    readimagefile("bgSenha.jpg", 0, 0, 300, 500);
    strcpy(rotulo, "Normal");
    drawRadioButton(rotulo, 10, 50, 0, COR);
    strcpy(rotulo, "Prioritaria");
    drawRadioButton(rotulo, 90, 50, 0, COR);
    strcpy(rotulo, "Emergencial");
    drawRadioButton(rotulo, 190, 50, 0, COR);
    strcpy(rotulo, "Cardiologista");
    drawRadioButton(rotulo, 10, 150, 0, COR);
    strcpy(rotulo, "Pediatra");
    drawRadioButton(rotulo, 10, 170, 0, COR);
    strcpy(rotulo, "Oftalmologista");
    drawRadioButton(rotulo, 10, 190, 0, COR);
    strcpy(rotulo, "Neurologista");
    drawRadioButton(rotulo, 10, 210, 0, COR);
    strcpy(rotulo, "Dermatologista");
    drawRadioButton(rotulo, 10, 230, 0, COR);
    strcpy(rotulo, "Gerar Aleatoriamente");
    drawRadioButton(rotulo, 10, 440, 0, COR);
    readimagefile("sair.jpg", 175, 370, 175+110, 370+51);
}

void drawRadioButton(char message[], int x, int y, int background, int foreground)
{
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);
    setcolor(foreground);
    //setbkcolor(background);
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
    //setbkcolor(background);
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

    if (ismouseclick(WM_LBUTTONUP))
    {
        clearmouseclick(WM_LBUTTONDOWN);
        clearmouseclick(WM_LBUTTONUP);
    }

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

void drawRadioButtonUnchecked(char message[], int x, int y, int background, int foreground)
{
    settextstyle(SANS_SERIF_FONT, HORIZ_DIR, 1);
    setcolor(foreground);
    //setbkcolor(background);
    outtextxy(x+14,y,message);
    setfillstyle(1,7);
    setlinestyle(0,1,2);
    setcolor(7);
    fillellipse(x,y+8,9,9);
    setcolor(8);
    circle(x,y+8,8);

    if (ismouseclick(WM_LBUTTONUP))
    {
        clearmouseclick(WM_LBUTTONDOWN);
        clearmouseclick(WM_LBUTTONUP);
    }

    setlinestyle(lineinfo.linestyle, lineinfo.upattern, lineinfo.thickness);
}

int contaExame(filaPaciente f, int exame,int medico)
{
    struct noPacientes *aux;
    int c=0;
    aux = f.inicioNoPacientes;
    while(aux!=NULL)
    {
        if(aux->dadoPaciente.senhaPaciente.tipoMedico==medico)
            if(aux->dadoPaciente.exame==exame)
                c++;

        aux=aux->proximo;
    }
    return c;
}
void printaSenha(Pacientes p)
{
    char numeroSenha[3]= {0};

    if(p.senhaPaciente.tipoFila==0)
        outtextxy(20,285, "N");
    if(p.senhaPaciente.tipoFila==1)
        outtextxy(20,285, "P");
    if(p.senhaPaciente.tipoFila==2)
        outtextxy(20,285, "E");
// 0 (C) = Cardiologista, 1 (P) = Pediatra, 2 (O) = Oftalmologista, 3 (N) = Neurologista, 4 (D) = Dermatologista
    if(p.senhaPaciente.tipoMedico==0)
        outtextxy(32,285, "C");
    if(p.senhaPaciente.tipoMedico==1)
        outtextxy(32,285, "P");
    if(p.senhaPaciente.tipoMedico==2)
        outtextxy(32,285, "O");
    if(p.senhaPaciente.tipoMedico==3)
        outtextxy(32,285, "N");
    if(p.senhaPaciente.tipoMedico==4)
        outtextxy(32,285, "D");
    itoa(p.senhaPaciente.numSenha,numeroSenha,10);// Converte INT para String
    Sleep(200);
    outtextxy(45,285,numeroSenha);


}

int contaMedico(filaPaciente f, int medico)
{
    struct noPacientes *aux;
    int c=0;
    aux = f.inicioNoPacientes;
    while(aux!=NULL)
    {
        if(aux->dadoPaciente.senhaPaciente.tipoMedico==medico)
            c++;

        aux=aux->proximo;
    }
    return c;
}
double contarTempoMedico(filaPaciente f,int exame,int medico)
{
    struct noPacientes *aux;
    double c=0.0;
    aux = f.inicioNoPacientes;
    fflush(stdin);
    while(aux!=NULL)
    {
        if(aux->dadoPaciente.senhaPaciente.tipoMedico==medico)
            if(aux->dadoPaciente.exame==exame)
                c += difftime(aux->dadoPaciente.horaSaida,aux->dadoPaciente.horaChegada);

        if(exame==-1)
          if(aux->dadoPaciente.senhaPaciente.tipoMedico==medico)
            c += difftime(aux->dadoPaciente.horaSaida,aux->dadoPaciente.horaChegada);

        aux=aux->proximo;
    }
    return c;
}
int contaFila(filaPaciente f, int fila)
{
    struct noPacientes *aux;
    int c=0;
    aux = f.inicioNoPacientes;
    while(aux!=NULL)
    {
        if(aux->dadoPaciente.senhaPaciente.tipoFila==fila)
        {
            c++;
        }

        aux=aux->proximo;
    }
    return c;
}
double contarTempoFila(filaPaciente f,int fila)
{
    struct noPacientes *aux;
    double c=0.0;
    aux = f.inicioNoPacientes;
    fflush(stdin);
    while(aux!=NULL)
    {
        if(aux->dadoPaciente.senhaPaciente.tipoFila==fila)
            c += difftime(aux->dadoPaciente.horaSaida,aux->dadoPaciente.horaChegada);

        aux=aux->proximo;
    }
    return c;
}

void create(filaPaciente *f)
{
    f->inicioNoPacientes=NULL;
}

char* printTime()
{
    time_t tempoCorrente;
    struct tm *infoTempo;
    char *buffer;
    time(&tempoCorrente);
    infoTempo = localtime(&tempoCorrente);
    buffer = (char*) malloc(12*sizeof(char));
    strftime(buffer,12,"%H:%M:%S",infoTempo);
    return buffer;
}

int push (filaPaciente *f, Pacientes p)
{
    struct noPacientes *aux;
    aux = (struct noPacientes*)malloc(sizeof(struct noPacientes));
    if (aux==NULL)
        return (FALSE);

    aux->dadoPaciente=p;
    aux->proximo=f->inicioNoPacientes;
    f->inicioNoPacientes=aux;
    return(TRUE);
}
void barChart(struct graph g)
{
    const int folga     = 5;
    const int escala    = 10;
    setbkcolor(BLACK);
    setcolor(WHITE);
    settextstyle(SANS_SERIF_FONT,HORIZ_DIR,1);
    // Plota de forma centralizada o título do gráfico.
    outtextxy(g.dimensoes.x1+(g.dimensoes.x2-g.dimensoes.x1-textwidth(g.titulo))/2, g.dimensoes.y1, g.titulo);

    // Reduz o espaço vertical da área do gráfico em razão da exibição do título.
    g.dimensoes.y1 += textheight(g.titulo) + folga;

    // Obtem o maior valor do conjunto de valores. A versão atual desta função só trabalhará com
    // valores positivos.
    float maior = g.dados[0].valor;
    for (int i = 1; i < g.tamanhoDaSerie; i++)
    {
        if (g.dados[i].valor > maior)
        {
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
    for (int i = 0, intervalo=maior/escala; i <= maior; i+=intervalo)
    {
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

    for (int i=0; i< g.tamanhoDaSerie; i++)
    {
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

/**
* Capturar a hora atual do sistema
* return <b>time_t</b> que representa a hora atual do sistema
* Author: Paulo Muniz de Ávila
*/
time_t getTimeSystem() {
     time_t rawtime, rawtimeret;
     struct tm * timeinfo;
     char buffer [80];
     time ( &rawtime );
     timeinfo = localtime ( &rawtime );
     rawtimeret = mktime(timeinfo);
     return rawtimeret;
}
