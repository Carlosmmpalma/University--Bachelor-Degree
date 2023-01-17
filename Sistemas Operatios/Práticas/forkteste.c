#include <stdio.h>
void ma(void){
int x=fork();
int y=fork();

printf("%d\n",x);
printf("%d\n",y);
}

int main(){
    ma();
}