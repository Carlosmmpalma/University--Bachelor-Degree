function intervalo(a,b){
    let arr=new Array();
    let j=0;

    for(let i=a;i<=b;i++){
        arr[j]=i;
        j++;
    }
    return arr;
}   

console.log(intervalo(1,10))