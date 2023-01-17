function dobro(x){
    let arr=new Array();

    for(let i=0;i<x.length;i++){
        arr[i]=x[i]*2;
    };
    return arr;
}

console.log(dobro([1, 2.1, 4, 5, -2, 3]));