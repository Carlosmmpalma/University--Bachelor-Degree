function estender(x,n){

    let arr=new Array();
    let aux=0;
    let i=0;

   
        if(x.length===n){
            return x;
        }else if(x.length>n){
            while(i<n){
                arr[i]=x[i];
                i++;
            }
            return arr;
        }else{
            while(i<x.length){
                arr[i]=x[i];
                aux++;
                i++;
            }
            while(aux<n){
                arr[aux]=0;
                aux++;
            }
            return arr;
        }
}

console.log(estender([1,-2.5,3,5,6], 4));