function pares(x){

    let arr=new Array();
    let j=0;

    for(let i=0;i<x.length;i++){
        if(x[i]%2===0){
            arr[j]=x[i];
            j++;
        }
    }
    return arr;
}

console.log(pares([1,2,3,4,5,6]))