#include <stdio.h>
#include <string.h>
#include <windows.h>

/*
Projeto - ORI

  0     1       2       3
USER AUTH USERNAME PASSWORDMD5
  0     1       2       3           4           5           6           7          8
USER CREATE USERNAME PASSWORDMD5 authCREATE authINSERT authUPDATE authSELECT authDELETE

Ex1: USER AUTH ANTONIO E8D95A51F3AF4A3B134BF6BB680A213A

EX2: USER CREATE ANTONIO E8D95A51F3AF4A3B134BF6BB680A213A 1 1 1 1 1

       returns:
   0 argumments exceeds maximum
   1 AUTH/CREATE SUCCESS
   2 ERROR USER EXISTS /INVALID LOGIN/PASS
   -1 error open/write
*/

int main (int argc, char *argv[])
{
    FILE *fp,*fpAux;
    char userName[50]="",userAux[50]="",userPassword[50]="",passAux[50]="",auxQuery[50]="INSERT INTO USERS VALUES ",buffer;
    int aux=0,userExists=0,passExists=0;

    fp = fopen("USERS.table","rt+");

    if((fp==NULL))
    {
        fclose(fp);
        printf("NULL");
        return(-1);
    }

    while(!feof(fp) && !userExists)
    {
        aux=0;
        do
        {
            buffer = fgetc(fp);
            if (buffer != ',' && !feof(fp) && buffer != '\n')
            {
                userAux[aux]=buffer;
                aux++;
            }
        }
        while (buffer != ',' && !feof(fp) && buffer != '\n');
        userAux[aux]='\0';

        if(!strcmp(userAux,argv[2]))
            userExists=1;
    }


    if(!strcmp(argv[1],"AUTH"))
    {
        if(!userExists)
        {
            fclose(fp);
            return(2);
        }
        aux=0;
        do
        {
            buffer=fgetc(fp);
            if(buffer!=',')
                passAux[aux]=buffer;
            aux++;
        }
        while (buffer != ',');

        if(!strcmp(passAux,argv[3]))
            passExists=1;

        if(userExists==1 && passExists==1)
        {
            fpAux = fopen("ResultSet.txt","wt");

            if((fpAux==NULL))
            {
                fclose(fp);
                fclose(fpAux);
                printf("NULL");
                return(-1);
            }

            do
            {
                buffer=fgetc(fp);
                if(buffer!='\n')
                    fprintf(fpAux,"%c",buffer);
            }
            while(buffer!='\n');
            fclose(fp);
            fclose(fpAux);
            return(1);
        }
        else
        {
            fclose(fp);
            return(2);
        }
    }

    if(!strcmp(argv[1],"CREATE"))
    {
        if(userExists==1)
        {
            fclose(fp);
            printf("\n -ERRO- USER EXISTS!");
            return(2);
        }

        for(aux=2; aux<=8; aux++)
        {
            strcat(auxQuery,argv[aux]);
            strcat(auxQuery," ");
        }

        aux = system(auxQuery);
    }

    //fclose(fp);
    return(aux);
}

