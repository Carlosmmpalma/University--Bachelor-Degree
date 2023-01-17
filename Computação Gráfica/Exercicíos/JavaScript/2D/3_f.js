function mapa(f,x){

    let arr=new Array();

    for(let i=0; i<x.length;i++){
        arr[i]=f(x[i]);
    }
    return arr;
}

console.log(mapa(x => 2 * x, [1, 2.1, 4, 5, -2, 3]));