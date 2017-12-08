#include <stdlib.h>
#include <stdio.h>
#include <string.h>
/* 0       1     2     3    4
   DELETE FROM PESSOA WHERE 12

   returns:
   0 row doesn't exists
   1 row deleted
   -1 error
*/

int main(int argc, char *argv[])
{
    FILE *fp, *fpTemp;
    char character;
    char tableTemp[200]="\0";
    char query[500]="\0";
    char row[1000]="\0";
    char rowAux[1000]="\0";
    int i = 0;
    int rowExists = 0;

    strcat(argv[2],".table");
    strcat(query,"SELECT . FROM ");
    strcat(query,argv[2]);
    strcat(query," WHERE ");
    strcat(query,argv[4]);
    //puts(query);

    rowExists = system(query);

    if(rowExists)
    {
        fp = fopen("ResultSet.txt","rt");
        do
        {
            character = fgetc(fp);
            if (!feof(fp))
            {
                row[i]=character;
                i++;
            }
        }
        while (!feof(fp));
        row[i]='\n';
        row[i+1]='\0';
        i = 0;

        fseek(fp,0,SEEK_SET);
        puts(row);

        fp = fopen (argv[2],"rt");

        strcpy(tableTemp,argv[2]);
        strcat(tableTemp, "temp");

        fpTemp = fopen(tableTemp,"wt");
        if (fpTemp == NULL)
            return -1;

        do
        {
            character = fgetc(fp);
            if (!feof(fp))
            {
                rowAux[i]=character;
                i++;
            }
            if (character == '\n' || feof(fp))
            {
                rowAux[i]='\0';
                i=0;
                if(strcmp(row,rowAux))
                    fwrite(&rowAux,strlen(rowAux),1,fpTemp);
            }
        }
        while (!feof(fp));
        rowAux[i]='\0';

        fclose(fp);
        fclose(fpTemp);

        remove(argv[2]);
        rename(tableTemp,argv[2]);

    }
    return rowExists;

}
