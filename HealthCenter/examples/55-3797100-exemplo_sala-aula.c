#include<stdio.h>
#include<windows.h>
void posicionaCursor(short x, short y)
{
  COORD coord; coord.X = x; coord.Y = y;
  SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

int main(){
    int x=0,i=1,y=0,j=1;
    while(1){
//        system("cls");
        posicionaCursor(x,y);
        printf("X");
        Sleep(10);
        x=x+i;
        y=y+j;
        if(x==80){
            x=78;
            i=-1;
        }else if(x==-1){
            x=1;
            i=1;
        }
        if(y==25){
            y=23;
            j=-1;
        }else if(y==-1){
            y=1;
            j=1;
        }
    }

}
