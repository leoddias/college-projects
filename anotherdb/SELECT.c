#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/*
EXEMPLO DE ARGUMENTO: SELECT . FROM PESSOA.TABLE OU SELECT . FROM PESSOA.TABLE WHERE 1

returns
-1 error
0 row doesn't exists
1 row founded/exists
*/
int main(int argc, char *argv[])
{
    int founded = 0;
    int i;
    int primaryKey = 0;
    int withWhere = 0;
    int lastPk = 0;
    FILE *fp, *fpBridge;
    char character;
    char pkAux[10]="\0";


    //for (i=0; i<argc; i++)
    //  printf("\nArgument %d = %s", i,argv[i]);
    primaryKey = atoi(argv[5]);
    //printf("\nPesquisar por : %d\n", primaryKey);
    if (argc > 4)
        withWhere=1;


    if (!strcmp(argv[1],".") && !strcmp(argv[2],"FROM"))
    {
        if (!withWhere) //Exibe tudo e grava tudo no arquivo consumidor (ResultSet.txt)
        {
            fp = fopen(argv[3], "rt");
            fpBridge = fopen("ResultSet.txt", "wt");
            if ((fp==NULL)||(fpBridge==NULL))
                founded = -1;
            else
            {
                while(!feof(fp))
                {
                    character = fgetc(fp);
                    if(!feof(fp))
                        fwrite(&character,sizeof(char),1,fpBridge);
                }
                founded = 1;
            }
        }
        if (withWhere)
        {
            primaryKey = atoi(argv[5]);

            if (!strcmp(argv[4],"WHERE"))
            {
                fp = fopen(argv[3], "rt");
                if (fp==NULL)
                    founded = -1;

                while(!feof(fp) && !founded)
                {
                    i = 0;
                    do
                    {
                        character = fgetc(fp);
                        if (character != ',' && !feof(fp) && character != '\n')
                        {
                            pkAux[i]=character;
                            i++;
                        }
                    }
                    while (character != ',' && !feof(fp) && character != '\n');

                    pkAux[i]='\0';
                    if (atoi(pkAux) == primaryKey)
                    {
                        fpBridge = fopen("ResultSet.txt", "wt");
                        if (fpBridge==NULL)
                            founded = -1;
                        strcat(pkAux,",");
                        fwrite(&pkAux,strlen(pkAux),1,fpBridge);
                        do
                        {
                            character = fgetc(fp);
                            if(!feof(fp) && character != 10)
                                fwrite(&character,sizeof(char),1,fpBridge);
                        }
                        while (character != '\n' && !feof(fp));
                        fclose(fpBridge);
                        founded = 1;
                    }
                    if (atoi(pkAux) != primaryKey)
                    {
                        do
                        {
                            character = fgetc(fp);
                        }
                        while (character != '\n' && !feof(fp));
                    }
                }
            }
        }
        fclose(fp);
        //system("notepad ResultSet.txt");
    }

    if (!strcmp(argv[1],"MAX") && !strcmp(argv[2],"FROM"))
    {
        fp = fopen(argv[3], "rt");
        if (fp==NULL)
            founded = -1;

        while(!feof(fp))
        {
            i = 0;
            do
            {
                character = fgetc(fp);
                if (character != ',' && !feof(fp) && character != '\n')
                {
                    pkAux[i]=character;
                    i++;
                }
            }
            while (character != ',' && !feof(fp) && character != '\n');
            pkAux[i]='\0';


            if (atoi(pkAux) > lastPk)
                lastPk = atoi(pkAux);

            do
            {
                character = fgetc(fp);
            }
            while (character != '\n' && !feof(fp));
        }
        founded = lastPk;

    }
return founded;
}
