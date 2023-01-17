function min(x){
    let aux=x[0];

    for(let i=0;i<x.length;i++){

        if(x[i]<aux){
            aux=x[i];
        }
    }
    return aux;
}
console.log(min([1, 2, 3, 4,-1]));