#include <stdio.h> // Biblioteca para funcoes de entrada e saida
#include <time.h> // Biblioteca para utilizar time(null)
#include <stdlib.h> // Biblioteca para uso das funcoes rand srand
#include <iostream.h> // Utilizar COORD de gotoxy e Sleep()
#define CIMA 72
#define BAIXO 80
#define ESQUERDA 75
#define DIREITA 77
#define ESC 27
#define linhamax 22 // Define o tamanho maximo das linhas
#define colunamax 60 // Define o tamanho maximo das colunas

////////////////////////////////////////
//      Trabalho de ATP - PUC POCOS   //
//  Jogo: DOSnake                     //
//  Desenvolvido no 1º		      //
//  periodo em Ciencias da Computacao://
// LEONARDO DANIEL DIAS		      //
// Professor: Fabiano                 //
////////////////////////////////////////

const int PER=002; // Define o desenho do personagem segundo a tabela ASCII
const int RASTRO=001; // Define o rastro deixado pelo personagem segundo a tabela ASCII
const int item=003; // Define o ponto deixado no mapa segundo a tabela ASCII
const int COL=178; // Define o caracter da Tabela ASCII usado para fazer as paredes
int pontos=12; // Variavel global para definir numero de pontos na tela
int perdeu=0; // Variavel pra ver se perdeu
int nivel=1; // Variavel de nivel
char fundo[linhamax][colunamax]; // Matriz utilizada pra fazer o background do jogo


int letecla(void); // Le a tecla pressionada
void desenhos(void); // Desenha as colunas e as informacoes da tela
void paredes(void); // Desenha laterais do fundo
int start(void); // Comeca o jogo
int gerar(void); // Gera numeros aleatorios dos pontos
void inicio(void); // Telas de boas vindas / primeira execucao
int final(void); // Tela de Fim, vecendor/perdedor
void gotoxy(int a,int b); // Posiciona o Cursor
void cls(void); // Mesmo que system cls porem em funcao

int main()
{
    int f=0;
    inicio();
    do
    {
        start();
        f=final();
    }
    while(f!=0);


}//Fim main()

int start()
{
    int ii,jj,i,j,tecla,contarp=0;
    int fim=1;
    cls();
    desenhos();
    system("color 1F");
    do
    {
        paredes(); // Desenha as laterais da matriz novamente.
////////////PRINTA A MATRIZ NA TELA - TEM QUE MODIFICAR ISSO /////////////////
        for(i=0; i<linhamax; i++)
        {
            for(j=0; j<colunamax; j++)
                printf("%c",fundo[i][j]);
            printf("\n");
        }
        gotoxy(3,62);
        printf("-----DOSnake-----");
        gotoxy(6,61);
        printf("Pontos restantes:");
        gotoxy(7,69);
        printf("%d",pontos-contarp);
        gotoxy(12,61);
        printf("Nivel Atual: %d",nivel);
        gotoxy(0,0);
//////////////FIM DE PRINTAR /////////////////////////

        for(i=0; i<linhamax; i++) // Procura aonde está o PER e armazena seu lugar em variaveis auxilares.
        {
            for(j=0; j<colunamax; j++)
            {
                if(fundo[i][j]==PER)
                {
                    ii=i;
                    jj=j;
                    break;
                }
            }
        }

        tecla = letecla();
        switch (tecla)
        {
        case CIMA:
            if(ii-1<=0)
            {
                fundo[ii][jj]=RASTRO;
                ii=21;

            }
            ////////////////COLISAO////////////////
            if(fundo[ii-1][jj]==RASTRO)
            {
                perdeu=1;
                fim=0;
                break;
            }
            ///////FIM COLISAO///////
            if(fundo[ii-1][jj]==item)
            {
                contarp++;
            }

            fundo[ii-1][jj]=PER;
            fundo[ii][jj]=RASTRO;
            break;
        case BAIXO:
            if(ii+1>=linhamax-1)
            {
                fundo[ii][jj]=RASTRO;
                ii=0;

            }
            ////////////////COLISAO////////////////
            if(fundo[ii+1][jj]==RASTRO)
            {
                perdeu=1;
                fim=0;
                break;
            }
            /////////////FIM COLISAO////////////
            if(fundo[ii+1][jj]==item)
                contarp++;

            fundo[ii+1][jj]=PER;
            fundo[ii][jj]=RASTRO;
            break;

        case ESQUERDA:
            if(jj-1<=0)
            {
                fundo[ii][jj]=RASTRO;
                jj=colunamax-1;

            }
            ////////////////COLISAO////////////////
            if(fundo[ii][jj-1]==RASTRO)
            {
                perdeu=1;
                fim=0;
                break;
            }
            /////////////FIM COLISAO////////////
            if(fundo[ii][jj-1]==item)
                contarp++;

            fundo[ii][jj-1]=PER;
            fundo[ii][jj]=RASTRO;
            break;

        case DIREITA:
            if(jj+1>=colunamax-1)
            {
                fundo[ii][jj]=RASTRO;
                jj=0;

            }
            ////////////////COLISAO////////////////
            if(fundo[ii][jj+1]==RASTRO)
            {
                perdeu=1;
                fim=0;
                break;
            }
            /////////////FIM COLISAO////////////
            if(fundo[ii][jj+1]==item)
                contarp++;

            fundo[ii][jj+1]=PER;
            fundo[ii][jj]=RASTRO;
            break;

        case ESC:
            perdeu=1;
            fim=0;
            break;
        }

        if(contarp==pontos)
        {
            perdeu=0;
            fim=0;
        }
    }while(fim!=0);
    Sleep(1000);
}

int gerar()
{
    int pontoi[pontos],pontoj[pontos],i,j,aux=0;
    int pontoii=0,pontojj=0;
    memset(pontoi,0, sizeof pontoi) ; // Zera o array pontoi
    memset(pontoj,0, sizeof pontoj) ; // Zera o array pontoj
    srand( (unsigned)time(NULL) ); // Gera uma seed para usar o rand()

    for(i=0; i<pontos; i++)
        pontoi[i]=4+(rand() % 16); // Vetor de linha que recebe valores aleatorios de 4~16

    for(j=0; j<pontos; j++)
        pontoj[j]=4+(rand() % 54); // Vetor de coluna que recebe valores aleatorios de 4~54

    for(i=0,j=0; i<pontos; i++,j++) // Coloca pontos aleatorios gerados pelos pontoi e pontoj na matriz fundo
        fundo[pontoi[i]][pontoj[j]]= item;

    for(i=0; i<linhamax; i++)
    {
        for(j=0; j<colunamax; j++)
        {
            if(fundo[i][j]==item)
                aux++;
        }
    }
    return(aux);
}

void desenhos(void)
{
    int i,j,x,xx;

///////////////////FAZ A MATRIZ ZERAR EM TODAS AS POSICOES/////////////////////
    for(i=0; i<linhamax; i++)
    {
        for(j=0; j<colunamax; j++)
            fundo[i][j]=0;
    }
/////////////////////////// FIM ///////////////////////////////////////////////
    fundo[1][1]=PER; // DEFINE O INICIO DO NUMERO 1

    do
    {
        x=gerar();
        if(x==pontos)
            xx=0;
        else
            xx=1;
    }
    while(xx!=0);

    paredes();
}

void paredes(void)
{
    int i,j;
    for(i=0; i<linhamax; i++) // Desenha as laterais
    {
        fundo[i][0]=COL;
        fundo[i][colunamax-1]=COL;
    }

    for(j=1; j<colunamax-1; j++) // Desenha linha superior/inferior
    {
        fundo[0][j]=COL;
        fundo[linhamax-1][j]=COL;
    }
}
int letecla()
{
    int tecla=0;
    tecla = getch();
    if(tecla == 224)
    {
        tecla = getch();
        return(tecla);
    }
    else
        return(tecla);
}

void inicio(void)
{
    int x;
    system("color 1F");

    printf("\n\t\t\xDB\xDB\xDB\xDB\xDB\xDB  \xDB\xDB\xDB\xDB\xDB\xDB\xDB  \xDB\xDB\xDB\xDB\xDB  \xDB     \xDB    \xDB    \xDB    \xDB \xDB\xDB\xDB\xDB\xDB\xDB\xDB \n\t\t\xDB     \xDB \xDB     \xDB \xDB     \xDB \xDB\xDB    \xDB   \xDB \xDB   \xDB   \xDB  \xDB       \n\t\t\xDB     \xDB \xDB     \xDB \xDB       \xDB \xDB   \xDB  \xDB   \xDB  \xDB  \xDB   \xDB       \n\t\t\xDB     \xDB \xDB     \xDB  \xDB\xDB\xDB\xDB\xDB  \xDB  \xDB  \xDB \xDB     \xDB \xDB\xDB\xDB    \xDB\xDB\xDB\xDB\xDB   \n\t\t\xDB     \xDB \xDB     \xDB       \xDB \xDB   \xDB \xDB \xDB\xDB\xDB\xDB\xDB\xDB\xDB \xDB  \xDB   \xDB       \n\t\t\xDB     \xDB \xDB     \xDB \xDB     \xDB \xDB    \xDB\xDB \xDB     \xDB \xDB   \xDB  \xDB       \n\t\t\xDB\xDB\xDB\xDB\xDB\xDB  \xDB\xDB\xDB\xDB\xDB\xDB\xDB  \xDB\xDB\xDB\xDB\xDB  \xDB     \xDB \xDB     \xDB \xDB    \xDB \xDB\xDB\xDB\xDB\xDB\xDB\xDB\n\n\t\t");
    for(x=0; x<50; x++)
        printf("%c",RASTRO);
    printf("%c\n",PER);
    printf("\n\t Controles: Setas direcionais Cima(%c) Baixo(%c) Direita(%c) Esquerda(%c)",030,031,26,27);
    printf("\n\t Objetivo: Coletar todos os pontos da tela, sem encostar em seu rastro !");
    printf("\n\t Lembre-se: Voce pode atravessar as paredes !");
    printf("\n\n\t\t\t\t Divirta-se!! Boa sorte!");
    printf("\n\n\t\t\t\t\t\t\tDesenvolvido por:\n\t\t\t\t\t\t\t\tLeonardo Dias\n\t\t\t\t\t\t\t\tMarcelo Assis\n\t\t\t\t\t\t\t\tLucas Marcelo\n\n");
    system("pause");
}

int final(void)
{
    int tecla=0,i;
    cls();
    if(perdeu==1)
    {
        do
        {
            system("cls");
            printf("\n  \xDB\xDB\xDB\xDB\xDB                          \xDB\xDB\xDB\xDB\xDB\xDB\xDB                      \n \xDB     \xDB   \xDB\xDB   \xDB    \xDB \xDB\xDB\xDB\xDB\xDB\xDB    \xDB     \xDB \xDB    \xDB \xDB\xDB\xDB\xDB\xDB\xDB \xDB\xDB\xDB\xDB\xDB  \n \xDB        \xDB  \xDB  \xDB\xDB  \xDB\xDB \xDB         \xDB     \xDB \xDB    \xDB \xDB      \xDB    \xDB \n \xDB  \xDB\xDB\xDB\xDB \xDB    \xDB \xDB \xDB\xDB \xDB \xDB\xDB\xDB\xDB\xDB     \xDB     \xDB \xDB    \xDB \xDB\xDB\xDB\xDB\xDB  \xDB    \xDB \n \xDB     \xDB \xDB\xDB\xDB\xDB\xDB\xDB \xDB    \xDB \xDB         \xDB     \xDB \xDB    \xDB \xDB      \xDB\xDB\xDB\xDB\xDB  \n \xDB     \xDB \xDB    \xDB \xDB    \xDB \xDB         \xDB     \xDB  \xDB  \xDB  \xDB      \xDB   \xDB  \n  \xDB\xDB\xDB\xDB\xDB  \xDB    \xDB \xDB    \xDB \xDB\xDB\xDB\xDB\xDB\xDB    \xDB\xDB\xDB\xDB\xDB\xDB\xDB   \xDB\xDB   \xDB\xDB\xDB\xDB\xDB\xDB \xDB    \xDB\n         ");
            printf("\n\n\t\t\a\a Voce Conseguiu alcancar o nivel: %d ! \n",nivel);
            printf("\n\n\t\t Deseja jogar novamente?  S ou N!? [ ]\b\b");
            system("color 40");
            Sleep(200);
            system("color 04");
            Sleep(200);
            system("color 02");
            Sleep(200);
            system("color 16");
            Sleep(200);
            system("color 04");
            Sleep(200);

            tecla=letecla();

            if(tecla==115)
            {
                nivel=1;
                pontos=12;
                i=tecla;
                return(1);
            }
            else if(tecla==110)
            {
                i=tecla;
                return(0);
            }

        }
        while(tecla!=115 || tecla!=110);
    }
    else
    {

        printf("\n\n\xDB\xDB    \xDB\xDB  \xDB\xDB\xDB\xDB\xDB\xDB\xDB  \xDB\xDB     \xDB\xDB       \xDB\xDB      \xDB\xDB \xDB\xDB\xDB\xDB \xDB\xDB    \xDB\xDB    \xDB\xDB\xDB\xDB \n \xDB\xDB  \xDB\xDB  \xDB\xDB     \xDB\xDB \xDB\xDB     \xDB\xDB       \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB\xDB   \xDB\xDB    \xDB\xDB\xDB\xDB \n  \xDB\xDB\xDB\xDB   \xDB\xDB     \xDB\xDB \xDB\xDB     \xDB\xDB       \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB\xDB\xDB  \xDB\xDB    \xDB\xDB\xDB\xDB \n   \xDB\xDB    \xDB\xDB     \xDB\xDB \xDB\xDB     \xDB\xDB       \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB \xDB\xDB \xDB\xDB     \xDB\xDB  \n   \xDB\xDB    \xDB\xDB     \xDB\xDB \xDB\xDB     \xDB\xDB       \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB\xDB\xDB         \n   \xDB\xDB    \xDB\xDB     \xDB\xDB \xDB\xDB     \xDB\xDB       \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB  \xDB\xDB   \xDB\xDB\xDB    \xDB\xDB\xDB\xDB \n   \xDB\xDB     \xDB\xDB\xDB\xDB\xDB\xDB\xDB   \xDB\xDB\xDB\xDB\xDB\xDB\xDB         \xDB\xDB\xDB  \xDB\xDB\xDB  \xDB\xDB\xDB\xDB \xDB\xDB    \xDB\xDB    \xDB\xDB\xDB\xDB \n        ");
        printf("\n\n\t\t Voce Avancou para o nivel %d \n",nivel+1);
        system("color 1A");
        Sleep(200);
        system("color 49");
        Sleep(200);
        system("color 28");
        Sleep(200);
        system("color 3F");
        Sleep(200);

        nivel++;
        pontos+=3;
        Sleep(3000);
        return(1);
    }
}

void gotoxy(int linha, int coluna)
{
    COORD point;
    point.X = coluna;
    point.Y = linha;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), point);
}

void cls(void)
{
    COORD coordScreen = {0, 0};
    DWORD cCharsWritten;
    CONSOLE_SCREEN_BUFFER_INFO csbi;
    DWORD dwConSize;
    HANDLE hConsole=GetStdHandle(STD_OUTPUT_HANDLE);
    GetConsoleScreenBufferInfo(hConsole, &csbi);
    dwConSize = csbi.dwSize.X * csbi.dwSize.Y;

    FillConsoleOutputCharacter(hConsole, TEXT(' '),
                               dwConSize, coordScreen, &cCharsWritten);

    GetConsoleScreenBufferInfo(hConsole, &csbi);

    FillConsoleOutputAttribute(hConsole, csbi.wAttributes,
                               dwConSize, coordScreen, &cCharsWritten);

    SetConsoleCursorPosition(hConsole, coordScreen);
}


