#include <stdlib.h>
main()
{
      int teclaPressionada1, teclaPressionada2;

      do
      {
          teclaPressionada1 = getche();
          printf("\nPrimeira Tecla Pressionada = %c %d\n", teclaPressionada1, teclaPressionada1);
          if (kbhit())
          {
              teclaPressionada2 = getche();
              printf("\nSegunda Tecla Pressionada = %c %d\n", teclaPressionada2, teclaPressionada2);

              // Testando uma das teclas de direção - Seta para Baixo
              if (teclaPressionada2 == 80)
                  printf("\n*** Foi pressionada a seta para baixo... ***\n");
          }
      } while (teclaPressionada1 != 27);

      printf("\n\nFim do Programa...");
      getchar();
}
