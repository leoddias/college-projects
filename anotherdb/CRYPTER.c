#include <openssl/aes.h>
#include <openssl/rand.h>
#include <openssl/hmac.h>
#include <openssl/buffer.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "tinydir.h" // Manipular arquivos de um diretorio.


//   0       1          2
//CRYPTER CRYPT chaveCriptografia
//CRYPTER DECRYPT chaveCriptografia

//Chave Utilizada: projetodeoriandb

// -1 ERROR
// 0 SUCESS

struct ctr_state
{
    unsigned char ivec[AES_BLOCK_SIZE];
    unsigned int num;
    unsigned char ecount[AES_BLOCK_SIZE];
};

AES_KEY key;

int bytes_read, bytes_written;
unsigned char indata[AES_BLOCK_SIZE];
unsigned char outdata[AES_BLOCK_SIZE];
unsigned char iv[AES_BLOCK_SIZE];
struct ctr_state state;

int init_ctr(struct ctr_state *state, const unsigned char iv[16])
{
    /* aes_ctr128_encrypt requires 'num' and 'ecount' set to zero on the
     * first call. */
    state->num = 0;
    memset(state->ecount, 0, AES_BLOCK_SIZE);

    /* Initialise counter in 'ivec' to 0 */
    memset(state->ivec + 8, 0, 8);

    /* Copy IV into 'ivec' */
    memcpy(state->ivec, iv, 8);
}


int encryptDirectory(char directory[], const unsigned char* cryptkey,int operation)
{
    int numberOfFiles=0,numberOfDir=0,aux=0,auxFile=0,auxDir=0;
    int returnAux=-1,i;
    char **nameFile,**nameDir;
    tinydir_dir dir;

    numberOfFiles = countFiles(directory);
    numberOfDir = countDir(directory);

    nameFile=malloc(numberOfFiles * sizeof(char *));
    for(i=0;i<numberOfFiles;i++)
        nameFile[i]=malloc(50*sizeof(char));

    nameDir=malloc(numberOfDir * (sizeof(char *) + sizeof(directory) + sizeof(char)) );
    for(i=0;i<numberOfDir;i++){
        nameDir[i]=malloc(50*sizeof(char));
    }

    tinydir_open(&dir, directory);

    while (dir.has_next)
    {
        tinydir_file file;
        tinydir_readfile(&dir, &file);

        if (!file.is_dir && strcmp(file.name,"..") && strcmp(file.name,"."))
        {
            strcpy(nameFile[auxFile],directory);
            strcat(nameFile[auxFile],"/");
            strcat(nameFile[auxFile],file.name);

            auxFile++;
        }
        if (file.is_dir && strcmp(file.name,"..") && strcmp(file.name,"."))
        {

            strcpy(nameDir[auxDir],directory);
            strcat(nameDir[auxDir],"/");
            strcat(nameDir[auxDir],file.name);
            auxDir++;

        }

        tinydir_next(&dir);
    }
    tinydir_close(&dir);

    for(aux=0; aux < numberOfFiles; aux++)
    {

        switch(operation){
        case 0:
            returnAux = encryptThis(nameFile[aux],cryptkey);
            break;
        case 1:
            returnAux = decryptThis(nameFile[aux],cryptkey);
            break;
        default:
            printf("ALGO DEU ERRADO");
            break;
        }
    }

    for(aux=0; aux < numberOfDir; aux++)
    {
        returnAux = encryptDirectory(nameDir[aux],cryptkey,operation);
    }

    return(returnAux);
}

int encryptThis(char nameFile[], const unsigned char* cryptkey)
{
    FILE *fp,*fpAux;
    char writeFile[50]="";
    strcpy(writeFile,nameFile);
    strcat(writeFile,".tmp");

     if(!RAND_bytes(iv, AES_BLOCK_SIZE))
    {
        printf("\nERRO AO GERAR BLOCOS ALEATORIOS!");
        return(-1);
    }

    fp = fopen(nameFile,"rb");
    fpAux = fopen(writeFile,"wb");

    if(fp==NULL || fpAux==NULL)
    {
        printf("FILE POINTER NULL ! !");
        fclose(fp);
        fclose(fpAux);
        return(-1);
    }

    fwrite(iv, 1, 8, fpAux);
    fwrite("\0\0\0\0\0\0\0\0", 1, 8, fpAux);
    if (AES_set_encrypt_key(cryptkey, 128, &key) < 0)
    {
        printf("ERRO NA CHAVE DE 128");
        fclose(fp);
        return(-1);
    }

    init_ctr(&state, iv);

    while(1)
    {
        bytes_read = fread(indata, 1, AES_BLOCK_SIZE, fp);
        AES_ctr128_encrypt(indata, outdata, bytes_read, &key, state.ivec, state.ecount, &state.num);

        bytes_written = fwrite(outdata, 1, bytes_read, fpAux);
        if (bytes_read < AES_BLOCK_SIZE)
        {
            break;
        }
    }

    fclose(fp);
    remove(nameFile);
    fclose(fpAux);
    rename(writeFile,nameFile);

    return(0);

}

int decryptThis(char nameFile[], const unsigned char* cryptkey)
{
    FILE *fp,*fpAux;
    char writeFile[50]="";
    strcpy(writeFile,nameFile);
    strcat(writeFile,".tmp");

    fp=fopen(nameFile,"rb");
    fpAux=fopen(writeFile,"wb");

    if(fp==NULL || fpAux==NULL)
    {
        printf("File POINTER is null.");
        fclose(fp);
        fclose(fpAux);
        return(-1);
    }

    fread(iv, 1, AES_BLOCK_SIZE, fp);

    //Initializing the encryption KEY
    if (AES_set_encrypt_key(cryptkey, 128, &key) < 0)
    {
        printf("IMPOSSIVEL DEFINIR CHAVE DE CRIPTOGRAFIA!");
        fclose(fp);
        fclose(fpAux);
        return(-1);
    }

    init_ctr(&state, iv);
    while(1)
    {
        bytes_read = fread(indata, 1, AES_BLOCK_SIZE, fp);
        AES_ctr128_encrypt(indata, outdata, bytes_read, &key, state.ivec, state.ecount, &state.num);

        bytes_written = fwrite(outdata, 1, bytes_read, fpAux);
        if (bytes_read < AES_BLOCK_SIZE)
        {
            break;
        }
    }


    fclose(fp);
    remove(nameFile);
    fclose(fpAux);
    rename(writeFile,nameFile);
    return(0);
}

int countFiles(char directory[])
{
    int c=0;
    tinydir_dir dir;
    tinydir_open(&dir, directory);

    while (dir.has_next)
    {
        tinydir_file file;
        tinydir_readfile(&dir, &file);

        if (!file.is_dir)
            c++;

        tinydir_next(&dir);
    }

    tinydir_close(&dir);
    return(c);
}
int countDir(char directory[])
{
    int c=0;
    tinydir_dir dir;
    tinydir_open(&dir, directory);

    while (dir.has_next)
    {
        tinydir_file file;
        tinydir_readfile(&dir, &file);

        if (file.is_dir && strcmp(file.name,"..") && strcmp(file.name,"."))
        {
            c++;
        }

        tinydir_next(&dir);
    }

    tinydir_close(&dir);
    return(c);
}

int main(int argc,char argv[])
{
    int returnAux=-1;
	printf("AES_BLOCK_SIZE: %d",AES_BLOCK_SIZE);

//    if(sizeof(argv[2]) != 16)
//    {
//        printf("\nTotal de Argumentos: %d \nTamanho de chave diferente de 16\nTOTAL DE: %d bits\n Valor: %s \n",argc,sizeof(argv[2]),argv[2]);
//        return(-1);
//    }

   // if(!strcmp(argv[1],"CRYPT"))
      //  returnAux = encryptDirectory("tabelas", (const unsigned char*)"projetooriandb",0);
 //   if(!strcmp(argv[1],"DECRYPT"))
       returnAux = encryptDirectory("tabelas", (const unsigned char*)"projetooriandb",0);

    return(returnAux);
}
