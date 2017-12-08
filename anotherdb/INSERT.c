#include <stdio.h>
#include <string.h>
#include <windows.h>

/*
Projeto - ORI
    0   1       2      3    4      5 . . . .
INSERT INTO TABELA VALUES VALUE1 VALUE2
Ex: INSERT INTO TABELA VALUES OCTAVIO DoHARDWARE

       returns:
   0 argumments exceeds maximum
   1 insert with success!
   -1 error open/write
*/

int main (int argc, char *argv[])
{
    FILE *fp,*fpAtt;
    char tableName[500]="",tableAtt[500]="",auxQuery[500]="SELECT MAX FROM ",character;
    int counterColumn=0,auxValue,aux=1;

    int i = 0;
    for(i=0;i<argc;i++)
    {
        printf("arg %d = %s\n", i, argv[i]);
    }

    strcat(tableName,argv[2]);
    strcat(tableName,".table");

    strcat(tableAtt,argv[2]);
    strcat(tableAtt,".att");

    fp = fopen(tableName,"rt+");
    puts(tableName);
    fpAtt = fopen(tableAtt, "rt");

    if(fp==NULL)
    {
        printf("\n - ERRO ! - \n Ao abrir tabela!");
        fclose(fp);
        //fclose(fpAtt);
        return(-1);
    }
    if (fpAtt==NULL)
    {
        printf("\n - ERRO ! - \n Ao abrir tabela de att!");
        //fclose(fp);
        fclose(fpAtt);
        return(-1);
    }

    do
    {
        character = fgetc(fpAtt);
        if (character == '\n')
            counterColumn++;
    }
    while (!feof(fpAtt));
    fclose(fpAtt);

    if((argc-4)!=counterColumn-1)
    {
        printf("\nERRO NUMERO DE ARGUMENTOS!");
        fclose(fp);
        return(0);
    }

    fseek(fp,0,SEEK_END);
    strcat(auxQuery,tableName);
    aux = system(auxQuery);
    fprintf(fp,"%d%s",aux+1,",");

    for(aux=0,auxValue=4; aux<counterColumn-1; aux++,auxValue++)
    {
        if(aux==counterColumn-2)
            fprintf(fp,"%s%s",argv[auxValue],"\n");
        else
            fprintf(fp,"%s%s",argv[auxValue],",");
    }




    //fputs("\n",fp);

    fclose(fp);
    return(1);
}
