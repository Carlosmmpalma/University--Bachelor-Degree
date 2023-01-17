function max(x){

    let aux=0;

    for(let i=0;i<x.length;i++){

        if(x[i]>aux){
            aux=x[i];
        }

    }
    return aux;
}

console.log(max([1, 2, 3, 4]));