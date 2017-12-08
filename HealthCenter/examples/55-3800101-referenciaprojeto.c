#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <windows.h>

#define TRUE 1
#define FALSE 0

typedef struct
{
    int   senha;
    char  horaChegada[12];
    float tempoEstimado;
} usuario;

struct no
{
    usuario dado;
    struct no *prox;
};

typedef struct
{
    struct no *topo;
} pilha;

char* printTime();
void  create(pilha *s);
int   push(pilha *s, usuario d);
int   pop(pilha *s, usuario *d);
void mostraPilha(pilha s);

main()
{
    usuario cidadao;
    int i;
    pilha normal, prioridade;

    create(&normal);
    create(&prioridade);

    mostraPilha(normal);
    mostraPilha(prioridade);

    srand(time(NULL));
    for (i=0; i < 10; i++)
    {
         Sleep(rand()%501);
         cidadao.senha = rand()%100;
         strcpy(cidadao.horaChegada, printTime());
         cidadao.tempoEstimado = 4.30 + i;

         if (cidadao.senha <= 50)
             push(&normal, cidadao);
         else
              push(&prioridade, cidadao);
    }

    printf("Normal\n");
    mostraPilha(normal);
    printf("\n\nPrioridade\n");
    mostraPilha(prioridade);

}

// Função que retorna uma string contendo a hora no formato HH:MM:SS
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

void create(pilha *s)
{
    s->topo=NULL;
}

int push(pilha *s, usuario d)
{
    struct no *aux;
    aux=(struct no *)malloc(sizeof(struct no));
    if (aux==NULL)
        return FALSE;

    aux->dado=d;
    aux->prox= s->topo;
    s->topo=aux;
    return TRUE;
}

int pop(pilha *s, usuario *d)
{
    struct no *aux;
    if (s->topo==NULL)
        return FALSE;

    aux= s->topo;
    s->topo =(s->topo)->prox;
    *d=aux->dado;
    free(aux);
    return TRUE;
}

void mostraPilha(pilha s)
{
    struct no *aux;
    aux = s.topo;

    if (aux == NULL)
        printf("\n\nPilha vazia...\n");
    else
    {
        while (aux != NULL)
        {
            printf("\nSenha = %d  Hora = %s  Tempo = %5.2f",
                   aux->dado.senha, aux->dado.horaChegada, aux->dado.tempoEstimado);
            aux = aux->prox;
        }
    }
}

int isEmpty(pilha s)
{
    if (s.topo == NULL)
        return TRUE;
    return FALSE;
}
