function linspace(a,b,n){

    let arr=new Array(n);
    let aux=(b-a)/(n-1);
    arr[0]=a;

    for(let i=1;i<n;i++){
        arr[i]=aux*i;
    }
    return arr;
}

console.log(linspace(0,1,3))