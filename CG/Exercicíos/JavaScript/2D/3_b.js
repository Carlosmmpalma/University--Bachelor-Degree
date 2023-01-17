function quadrado(x){
    
    let arr=new Array();

    for(let i=0;i<x.length;i++){
        arr[i]=Math.pow(x[i],2);
    }
    return arr;
}

console.log(quadrado([1, -2.5, 0.4]));