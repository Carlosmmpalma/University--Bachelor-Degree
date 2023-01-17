function positivos(x){

    let arr=new Array();
    let j=0;

    for(let i=0;i<x.length;i++){
        if(x[i]>0){
            arr[j]=x[i];
            j++;
        }
    }
    return arr;
}

console.log(positivos([-3,-2,-1,0,1,2,3]))