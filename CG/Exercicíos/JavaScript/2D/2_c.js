function limite_sup(x,a){
    let arr=new Array();
    let j=0;

    for(let i=0;i<x.length;i++){
        if(x[i]<=a){
            arr[j]=x[i];
            j++;
        }
    }
    return arr;
}

console.log(limite_sup([-2,-1,0,1,2],0))