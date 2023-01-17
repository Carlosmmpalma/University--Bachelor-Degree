function crescente(x){

    let aux=0;

    for(let i=0;i<x.length;i++){
        for(let j=i+1;j<x.length;j++){
            if(x[i]>x[j]){
                aux=x[i];
                x[i]=x[j];
                x[j]=aux
            }
        }

    }
    return x;

}

console.log(crescente([1, 10, 1,3,20]))