#include <stdio.h>
#include <math.h>

/*
            Aula de ORI - Criptografia
            Leonardo Daniel Dias
*/

main()
{
    FILE *fp,*fpC;
    int lengthFP=0;
    int aux=0;
    int i,j;
    char **array;
    char buffer=' ';
    char bufferFile[10000];

    fp = fopen("File.txt","rt");
    fpC = fopen("cryptedFile.txt","wt");
    if(fp==NULL || fpC == NULL)
    {
        printf("ERRO");
        exit(1);
    }

    do
    {
        buffer = fgetc(fp);
        if(!feof(fp))
        {
            bufferFile[lengthFP]=buffer;
            lengthFP++;
        }
    }
    while(!feof(fp));
    bufferFile[lengthFP]='\0';

    fclose(fp);
    fp = fopen("File.txt","wt"); // Clean File.txt
    fclose(fp);

    if(lengthFP>10000)
    {
        printf("ERRO, TAMANHO EXEDIDO");
        exit(1);
    }

    while(modf(sqrt(lengthFP), &aux) !=0 )
    {
        lengthFP++;
        strcat(bufferFile,"%");
    }

    lengthFP = sqrt(lengthFP);

    array = malloc(lengthFP * sizeof(char *));

    for(i=0; i<lengthFP; i++)
        array[i]=malloc(lengthFP * sizeof(char));

    for(i=0,aux=0; i<lengthFP; i++)
    {
        for(j=0; j<lengthFP; j++)
        {
            array[i][j]=bufferFile[aux];
            aux++;
        }
    }

    for(i=0; i<lengthFP; i++)
    {
        for(j=0; j<lengthFP; j++)
        {
            fprintf(fpC,"%c",array[j][i]);
        }
    }
    fclose(fpC);
}
