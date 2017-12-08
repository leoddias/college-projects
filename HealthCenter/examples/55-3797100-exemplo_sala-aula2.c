#include<stdio.h>
#include<windows.h>
void posicionaCursor(short x, short y)
{
  COORD coord; coord.X = x; coord.Y = y;
  SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

int main(){
    char comando;
    char color[9];
    char c1,c2;
    int x=0,y=0;
    int estado=0;
    //0 -> escrevendo
    //1 -> apagando
    //2 -> Caneta susp.
    char letra='X';
    while(1){
        posicionaCursor(x,y);
        switch(estado){
            case 0:
                printf("%c",letra);
                break;
            case 1:
                printf(" ");
                break;
            case 2:
                break;
        }
        comando=getch();
        comando=getch();
        switch(comando){
            case 72:
                if(y>0)
                    y--;
                else
                    printf("\a");
                break;
            case 80:
                if(y<25)
                    y++;
                else
                    printf("\a");
                break;
            case 75:
                if(x>0)
                    x--;
                else
                    printf("\a");
                break;
            case 77:
                if(x<79)
                    x++;
                else
                    printf("\a");

                break;
            case 59:
                estado=0;
                break;
            case 60:
                estado=1;
                break;
            case 61:
                estado=2;
                break;
            case 62:
                letra=getch();
                break;
            case 63: //F5
                c1=getch();
                c2=getch();
                sprintf(color,"color %c%c",c1,c2);
                system(color);
                break;
        }
    }
}
