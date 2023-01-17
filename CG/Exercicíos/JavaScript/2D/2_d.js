/*function f(x){
    return x%2===0
}*/

function filtro(f,x){
    let arr=new Array();
    let j=0;

    for(let i=0;i<x.length;i++){
        if(f(x[i])){
            arr[j]=x[i];
            j++;
        }
    }
    return arr;
}

console.log( filtro(x => x % 2 === 0, [1, 2, 4, 5, 2, 3]) )