function aleatorios(n){
    let arr=new Array(n);

    for(let i=0;i<n;i++){
        arr[i]=Math.random()*20;
    }
    return arr;
}

console.log(aleatorios(10))