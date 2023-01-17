function soma(x){
    let soma=0;
    
    for(let i=0;i<x.length;i++){
        soma+=x[i];
    }
    return soma;
}

console.log(soma([1, 2, 3, 4]));