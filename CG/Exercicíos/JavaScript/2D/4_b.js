function cadeia(x,y){

    return x.concat(y);
}

function cabeca(n,x){

    return x.slice(0,n);
}

function cauda(n,x){

    return x.slice(n-1,x.length);
}

console.log(cadeia([1, 2.1, 4], [5, -2, 3]));
console.log(cabeca(5,[1, 2.1, 4,5,4,3,2,4]))
console.log(cauda(1,[1, 2.1, 4,24,23,22]));

