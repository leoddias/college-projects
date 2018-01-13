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

    fp = fopen("cryptedFile.txt","rt");
    fpC = fopen("File.txt","wt");

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
    fp = fopen("cryptedFile.txt","wt"); // Clean cryptedFile.txt
    fclose(fp);

    while(modf(sqrt(lengthFP), &aux) !=0 )
        lengthFP++;

    lengthFP = sqrt(lengthFP);

    array = malloc(lengthFP * sizeof(char *));

    for(i=0; i<lengthFP; i++)
        array[i]=malloc(lengthFP * sizeof(char));

    for(i=0,aux=0; i<lengthFP; i++)
    {
        for(j=0; j<lengthFP; j++)
        {
            array[j][i]=bufferFile[aux];
            aux++;
        }
    }

    for(i=0; i<lengthFP; i++)
    {
        for(j=0; j<lengthFP; j++)
        {
            if(array[i][j]!='%')
                fprintf(fpC,"%c",array[i][j]);
        }
    }

    fclose(fpC);
}
