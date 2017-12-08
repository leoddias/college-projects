#include <stdio.h>
#include <time.h>

time_t getTimeSystem();
char * printTimeNow();

int main ()
{

  time_t inicio, fim;
  float delay = 1.0;
  int i = 0;
  double dif;
  char nome[50];

  inicio = getTimeSystem();
  printf("Digite seu nome: ");
  scanf("%s",&nome);
  fim = getTimeSystem();
  dif = difftime (fim,inicio);

  printf ("Oi %s agora sao %s\n",nome,printTimeNow());
  printf ("Voce levou %.2lf seconds para digitar seu nome\n", dif );


  fflush(stdin);
  getchar();
  
  return 0;
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


/**
* retorna a hora atual no formato HH:MM:SS
* Parameter char * hora = Recebe o buffer para retornar a hora
* Author: Paulo Muniz de Ávila
*/
char * printTimeNow() {
  time_t rawtime, rawtimeret;
  struct tm * timeinfo;
  char buffer[80];  char hora[80];

  time ( &rawtime );
  timeinfo = localtime ( &rawtime );
  strftime (buffer,80,"%H:%M:%S",timeinfo);

  //descomente as duas linhas a seguir e comente a linha return buffer
  //para executar no dev-c++
  //strcpy(hora,buffer);
  //return hora ;
  return buffer;

}
